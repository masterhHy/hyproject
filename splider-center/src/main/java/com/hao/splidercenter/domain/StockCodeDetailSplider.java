package com.hao.splidercenter.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONObject;
import com.hao.common.utils.UUID;
import com.hao.splidercenter.dao.StockCodeDao;
import com.hao.splidercenter.dao.StockCodeDetailDao;
import com.hao.splidercenter.po.StockCode;
import com.hao.splidercenter.po.StockCodeDetail;
import com.hao.splidercenter.utils.OutputWriterUtils;
import com.hao.splidercenter.utils.splider.Splider;
import com.hao.splidercenter.utils.splider.handler.JsonHandler;
import com.hao.splidercenter.utils.splider.thread.DefaultMonitor;
import com.hao.splidercenter.utils.splider.thread.HttpConnectionManager;
import com.hao.splidercenter.utils.splider.thread.SpliderTaskVo;
import com.hao.splidercenter.utils.splider.thread.TaskRun;

@Component
public class StockCodeDetailSplider implements SpliderTask {
	@Autowired
	private StockCodeDao stockCodeDao;
	@Autowired
	private StockCodeDetailDao stockCodeDetailDao;
	
	@Autowired
	private HttpConnectionManager pools;
	@Autowired
	private DruidDataSource ds;
	
	
	@Override
	public void run() {
		OutputWriterUtils.deleteDir(StockCodeDetail.class);
		String pubDate="";//空串为全部
		List<StockCodeDetail> all = stockCodeDetailDao.findAll();
		if(all.size()!=0){
			pubDate = OutputWriterUtils.getCurrentPublicDate();
		}
		List<StockCode> codeList = stockCodeDao.findAll();
		Map<String,String> kv= new HashMap<>();
		for (StockCode stockCode : codeList) {
			kv.put(stockCode.getCode(),stockCode.getId() );
		}
		//爬去当前季度 的任务列表
		List<SpliderTaskVo> list = this.runByPublicDay(pubDate, kv);
		if(StringUtils.isNotBlank(pubDate)){
			//爬去上一季度任务列表
			String upDate = OutputWriterUtils.getupPublicDate();
			List<SpliderTaskVo> list2 = this.runByPublicDay(upDate, kv);
			list.addAll(list2);
		}
		
		
		StockCodeDetailMonitor m = new StockCodeDetailMonitor(ds);
		m.max=list.size();
		TaskRun.run(list,m,pools);
		
		//入库完成后 去重！！！
		List<Map<String, Object>> idList = stockCodeDetailDao.getDoubleDataIds();
		for (Map<String, Object> ids : idList) {
			String[] objArr = ids.get("ids").toString().split(",");
			for (int i = 1; i < objArr.length; i++) {
				stockCodeDetailDao.deleteById(objArr[i]);
			}
		}
		
	}
	
	
	private List<SpliderTaskVo> runByPublicDay(String pubDate,Map<String,String> kv){
		String url="http://dcfm.eastmoney.com/em_mutisvcexpandinterface/api/js/get";
		Map<String,Object> param = new HashMap<>();
		param.put("type", "YJBB20_YJBB");
		param.put("token", "70f12f2f4f091e459a279469fe49eca5");
		param.put("st", "parentnetprofit");
		param.put("rt", 51155876);
		param.put("sr", -1);
		param.put("p", 1);
		param.put("ps", 5000);
		param.put("js", "(tp)");
		String filter="(securitytypecode%20in%20(%27058001001%27,%27058001002%27))(reportdate=^"+pubDate+"^)";
		param.put("filter", filter);
		//先获取总页数
		final List<Integer> pl= new ArrayList<>();
		Splider s = new Splider((String data)->{
			if(StringUtils.isNotBlank(data)){
				pl.add(Integer.parseInt(data));
			}else{
				pl.add(1);
			}
		});
		s.readUrl(url, "get", param);
		List<SpliderTaskVo> list = new ArrayList<>();
		for (int i = 1; i <= pl.get(0); i++) {
			Map<String,Object> para = new HashMap<>();
			para.putAll(param);
			para.put("js", "(x)");
			param.put("p", i);
			StockCodeDetailHandler handler = new StockCodeDetailHandler( stockCodeDetailDao);
			handler.setKv(kv);
			
			SpliderTaskVo v = new SpliderTaskVo();
			v.setUrl(url);
			v.setParams(para);
			v.setHandler(handler);
			v.setType("get");
			list.add(v);
		}
		
		return list;
		
		
	}
	
	

}

class StockCodeDetailMonitor extends DefaultMonitor<StockCodeDetail>{

	public StockCodeDetailMonitor(DruidDataSource ds) {
		super(ds);
		// TODO Auto-generated constructor stub
	}

	int max=0;
	int num=0;

	@Override
	public void currendURL(String url) {
		num++;
		System.out.println("当前完成率:"+(num*1.0/max*100)+"%");
	}

	@Override
	public void endToDo(List<SpliderTaskVo> error) {
		
		
	}

	
	


	
	
}

class StockCodeDetailHandler extends JsonHandler{
	private StockCodeDetailDao stockCodeDetailDao;
	private Logger logger = LoggerFactory.getLogger(StockCodeDetailHandler.class);
	public StockCodeDetailHandler(StockCodeDetailDao stockCodeDetailDao) {
		this.stockCodeDetailDao = stockCodeDetailDao;
	}
	private Map<String,String> kv = new HashMap<>();
	public void setKv(Map<String,String> kv) {
		this.kv = kv;
	}
	
	private List<StockCodeDetail> list = new ArrayList<>();
	private int num;
	
	
	@Override
	public void webData(JSONObject json) {
		String code = json.get("scode").toString();
		String codeId = kv.get(code);
		if(codeId==null){
			return;
		}
		String pubDate = json.get("reportdate").toString().split("T")[0];
		
		StockCodeDetail d = stockCodeDetailDao.findByPubDateAndStockCodeId(pubDate, codeId);
		if(d!=null){
			//如果这条数据的prfit_percent没有 则更新这条数据
			String currentPublicDate = OutputWriterUtils.getCurrentPublicDate();
			String upPublicDate = OutputWriterUtils.getupPublicDate();
			if(d.getProfitPercent()==null&&(d.getPubDate().equals(currentPublicDate)||d.getPubDate().equals(upPublicDate))){
				try {
					Double profit = Double.parseDouble(json.get("parentnetprofit").toString());
					d.setProfit(profit/100000000);
				} catch (Exception e) {
					logger.info("parentnetprofit出错--》"+json.get("parentnetprofit").toString());
					System.out.println("parentnetprofit出错--》"+json.get("parentnetprofit").toString());
				}
				try {
					Double profitPercent = Double.parseDouble(json.get("sjltz").toString());
					d.setProfitPercent(profitPercent);
				} catch (Exception e) {
					logger.info("parentnetprofit出错--》"+json.get("sjltz").toString());
					System.out.println("sjltz出错--》"+json.get("sjltz").toString());
					
				}
				d.setzUpdateTime(new Date());
				stockCodeDetailDao.save(d);
				num++;
				if(num>3000){
					stockCodeDetailDao.flush();
					num=0;
				}
				
			}
			//更新这条数据
			return;
		}
		
		StockCodeDetail scd= new StockCodeDetail();
		scd.setId(UUID.uuid32());
		scd.setStockCodeId(codeId);
		scd.setPubDate(pubDate);
		
		try {
			Double profit = Double.parseDouble(json.get("parentnetprofit").toString());
			scd.setProfit(profit/100000000);
		} catch (Exception e) {
			logger.info("parentnetprofit出错--》"+json.get("parentnetprofit").toString());
			System.out.println("parentnetprofit出错--》"+json.get("parentnetprofit").toString());
		}
		try {
			Double profitPercent = Double.parseDouble(json.get("sjltz").toString());
			scd.setProfitPercent(profitPercent);
		} catch (Exception e) {
			logger.info("parentnetprofit出错--》"+json.get("sjltz").toString());
			System.out.println("sjltz出错--》"+json.get("sjltz").toString());
			
		}
		
		
		list.add(scd);
	}

	@Override
	public void endToDo() {
		OutputWriterUtils.outPut(list);
	}

	/*
	 * http://data.eastmoney.com/bbsj/201806/yjbb/0/2.html
	 * {
        "scode":"601398",股票代码
        "reportdate":"2017-12-31T00:00:00",发布日期
        "basiceps":0.79,每股收益(元)
        "totaloperatereve":726502000000,营业收入（元）
        "ystz":7.48804171,营业收入同比增长
        "yshz":9.853,营业收入 季度环比
        "parentnetprofit":286049000000,净利润（元）
        "sjltz":2.80324458,净利润同比增长
        "sjlhz":-22.6041,净利润 季度环比
        "roeweighted":14.35,净资产收益率%
        "bps":5.73,每个净资产（元）
        "mgjyxjje":2.1628801,每股经营现金流量（元）
        "xsmll":"-",销售毛利率%
        "assigndscrpt":"10派2.408元(含税,扣税后2.1672元)",利润分配
    },*/

	
	
}
