package com.hao.splidercenter.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hao.common.utils.UUID;
import com.hao.splidercenter.dao.StockCodeDao;
import com.hao.splidercenter.dao.StockCodeDayDataDao;
import com.hao.splidercenter.dao.StockCodeDetailDao;
import com.hao.splidercenter.po.StockCodeDayData;
import com.hao.splidercenter.po.StockCodeDetail;
import com.hao.splidercenter.utils.OutputWriterUtils;
import com.hao.splidercenter.utils.splider.handler.JsonHandler;
import com.hao.splidercenter.utils.splider.thread.DefaultMonitor;
import com.hao.splidercenter.utils.splider.thread.HttpConnectionManager;
import com.hao.splidercenter.utils.splider.thread.SpliderTaskVo;
import com.hao.splidercenter.utils.splider.thread.TaskRun;

@Component
public class CodeDayDataSplider implements SpliderTask {
	@Autowired
	private StockCodeDao stockCodeDao;
	@Autowired
	private StockCodeDetailDao stockCodeDetailDao;
	@Autowired
	private StockCodeDayDataDao stockCodeDayDataDao;
	
	@Autowired
	private HttpConnectionManager pools;
	@Autowired
	private DruidDataSource ds;
	
	
	
	@Override
	public void run() {
		OutputWriterUtils.deleteDir(StockCodeDayData.class);
		//当前季度时间
		String currentPublicDay=OutputWriterUtils.getCurrentPublicDate();
		//上一季度时间
		String upDay = OutputWriterUtils.getupPublicDate();
		
		List<StockCodeDetail> list = stockCodeDetailDao.findByPubDateAndPriceIsNull(currentPublicDay);
		List<StockCodeDetail> list2 =stockCodeDetailDao.findByPubDateAndPriceIsNull(upDay);
		list.addAll(list2);
		
		List<SpliderTaskVo> tasks = new ArrayList<>();
		for (StockCodeDetail detail : list) {
			String endDay = detail.getPubDate();
			String startDay = this.getStartDay(endDay);
			String stockCode =stockCodeDao.findById(detail.getStockCodeId()).get().getCode();
			
			SpliderTaskVo v = new SpliderTaskVo();
			v.setUrl("http://q.stock.sohu.com/hisHq");
			Map<String,Object> params = new HashMap<>();
			params.put("code", "cn_"+stockCode);
			params.put("start", startDay.replace("-", ""));
			params.put("end", endDay.replace("-", ""));
			params.put("stat", 1);
			params.put("order", "D");
			params.put("period", "d");
			v.setParams(params);
			v.setType("get");
			v.setUnicode("GBK");
			CodeDayDataHandler handler = new CodeDayDataHandler(stockCodeDetailDao,stockCodeDayDataDao);
			handler.setCodeId(detail.getStockCodeId());
			handler.setStartDate(startDay);
			handler.setPubDate(endDay);
			v.setHandler(handler);
			tasks.add(v);
		}
		CodeDayDataMonitor m = new CodeDayDataMonitor(ds);
		m.max=list.size();
		TaskRun.run(tasks, m, pools);
		
		
	}

	
	private String getStartDay(String endDate){
		String idate = endDate.substring(endDate.indexOf("-")+1);
		List<String> date = new ArrayList<>();
		date.add("03-31");
		date.add("06-30");
		date.add("09-30");
		date.add("12-31");
		int index = date.indexOf(idate);
		String startDate="";
		if(index==0){
			startDate= endDate.substring(0, endDate.indexOf("-")+1)+"01-01";
		}else {
			startDate= endDate.substring(0, endDate.indexOf("-")+1)+date.get(index-1);
		}
		return startDate;
		
	}
}

class CodeDayDataHandler extends JsonHandler{
	private StockCodeDetailDao stockCodeDetailDao;
	private StockCodeDayDataDao stockCodeDayDataDao;
	public CodeDayDataHandler(StockCodeDetailDao stockCodeDetailDao,StockCodeDayDataDao stockCodeDayDataDao) {
		this.stockCodeDetailDao=stockCodeDetailDao;
		this.stockCodeDayDataDao=stockCodeDayDataDao;
	}
	
	private String codeId;
	private String pubDate;
	private String startDate;
	
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}


	@Override
	public void webData(JSONObject json) {
		
		List<StockCodeDayData> dataList = stockCodeDayDataDao.findByStockCodeIdAndOpenDateBetween(codeId, startDate, pubDate);
		if(dataList.size()==0){
			List<StockCodeDayData> data = new ArrayList<>();
			JSONArray arr = (JSONArray) json.get("hq");
			for (Object objList : arr) {
				List<String> list = (List<String>)objList;
				StockCodeDayData scdd = new StockCodeDayData();
				scdd.setAmount(dealDouble(list.get(8))/10000);
				scdd.setChangeHand(dealDouble(list.get(9)));
				scdd.setClosePrice(dealDouble(list.get(2)));
				scdd.setFinalPercent(dealDouble(list.get(4)));
				scdd.setId(UUID.uuid32());
				scdd.setLowPrice(dealDouble(list.get(5)));
				scdd.setNum(dealDouble(list.get(7)));
				scdd.setOpenDate(list.get(0));
				scdd.setOpenPrice(dealDouble(list.get(1)));
				scdd.setStockCodeId(codeId);
				scdd.setTopPrice(dealDouble(list.get(6)));
				data.add(scdd);
			}
			OutputWriterUtils.outPut(data);
		}
		
		
		
		StockCodeDetail detail = stockCodeDetailDao.findByPubDateAndStockCodeId(pubDate, codeId);
		if(detail!=null){
			List<Object> list = (List<Object>) json.get("stat");
			Double n = dealDouble(list.get(6).toString());
			Double a = dealDouble(list.get(7).toString());
			Double avgprice = a*100/n;
			detail.setPrice(avgprice);
			detail.setzUpdateTime(new Date());
			stockCodeDetailDao.save(detail);
		}
	}
	
	private Double dealDouble(String s){
		s=s.replace("%", "");
		Double d =null;
		try {
			d = Double.parseDouble(s);
			
		} catch (Exception e) {
			System.out.println("转换出错--》"+s);
		}
		return d;
	}
	/*
		{"status":0,
			"hq":[
					["2013-12-31","80.00","79.00","-1.22","-1.52%","77.20","80.00","20104","15832.18","2.77%"]
				],
			"code":"cn_300228",
			"stat":["累计:","2013-09-30至2013-12-31","1.55","2.00%",62.43,81.33,892944,655138.82,"122.87%"]
		}
	 * 
	 * hq :日期	开盘	收盘	涨跌额	涨跌幅	最低	最高	成交量(手)	成交金额(万)	换手率
	 * 
	 */
	

	@Override
	public void endToDo() {
		// TODO Auto-generated method stub
		
	}


	
	
}



class CodeDayDataMonitor extends DefaultMonitor<StockCodeDayData>{


	public CodeDayDataMonitor(DruidDataSource ds) {
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
