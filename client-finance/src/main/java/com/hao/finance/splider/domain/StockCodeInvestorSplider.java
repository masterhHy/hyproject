package com.hao.finance.splider.domain;

import com.alibaba.druid.pool.DruidDataSource;
import com.hao.common.utils.UUID;
import com.hao.finance.splider.dao.InvestorDao;
import com.hao.finance.splider.dao.StockCodeDao;
import com.hao.finance.splider.dao.StockCodeInvestorDao;
import com.hao.finance.splider.po.Investor;
import com.hao.finance.splider.po.StockCode;
import com.hao.finance.splider.po.StockCodeInvestor;
import com.hao.finance.splider.utils.OutputWriterUtils;
import com.hao.finance.splider.utils.splider.handler.HtmlHandler;
import com.hao.finance.splider.utils.splider.thread.DefaultMonitor;
import com.hao.finance.splider.utils.splider.thread.HttpConnectionManager;
import com.hao.finance.splider.utils.splider.thread.SpliderTaskVo;
import com.hao.finance.splider.utils.splider.thread.TaskRun;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class StockCodeInvestorSplider implements SpliderTask {
	@Autowired
	private StockCodeDao stockCodeDao;
	
	@Autowired
	private InvestorDao investorDao;
	@Autowired
	private StockCodeInvestorDao stockCodeInvestorDao;
	@Autowired
	private HttpConnectionManager pools;
	@Autowired
	private DruidDataSource ds;
	
	Logger logger = LoggerFactory.getLogger(StockCodeInvestorSplider.class);
	
	@Override
	public void run() {
		OutputWriterUtils.deleteDir(StockCodeInvestor.class);
		List<StockCode> codeList = stockCodeDao.findAll();
		
		List<SpliderTaskVo> list = new ArrayList<>();
		for (StockCode stockCode : codeList) {
			StockCodeInvestorHandler handler = new StockCodeInvestorHandler(investorDao,stockCodeInvestorDao,stockCode.getId());
			String url ="http://stockpage.10jqka.com.cn/"+stockCode.getCode()+"/position/";
			
			SpliderTaskVo v = new SpliderTaskVo();
			v.setUrl(url);
			v.setHandler(handler);
			v.setType("get");
			v.setParams(null);
			list.add(v);
		}
		InvestorMonitor m = new InvestorMonitor(ds);
		m.max=list.size();
		TaskRun.run(list,m,pools);
	}
	
	

}

class InvestorMonitor extends DefaultMonitor<StockCodeInvestor> {
	public InvestorMonitor(DruidDataSource ds) {
		super(ds);
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
		// TODO Auto-generated method stub
		
		
	}
	
}

class StockCodeInvestorHandler extends HtmlHandler {
	private InvestorDao investorDao;
	private StockCodeInvestorDao stockCodeInvestorDao;
	private String codeId;
	public StockCodeInvestorHandler(InvestorDao investorDao, StockCodeInvestorDao stockCodeInvestorDao, String codeId) {
		this.investorDao=investorDao;
		this.stockCodeInvestorDao=stockCodeInvestorDao;
		this.codeId=codeId;
	}
	
	@Override
	public void webData(Document doc) {
		Element root = doc.getElementById("holdetail");
		Elements pubDateEle = root.getElementsByClass("fdate");
		for (Element element : pubDateEle) {
			List<StockCodeInvestor> saveList= new ArrayList<>();
			String pubDate = element.html();
			List<StockCodeInvestor> haveSplider = stockCodeInvestorDao.findByPublicDateAndSpliderStockCodeId(pubDate, codeId);
			
			/*if(haveSplider.size()>0&&!OutputWriterUtils.getCurrentPublicDate().equals(pubDate)){
				//已经爬取过
				System.out.println("已经爬过了！！！");
				continue;
			}*/
			String targ = element.attr("targ");//organ_1
			Element dataTableEle = root.getElementById(targ);//获取对应table对象
			Elements trsEle = dataTableEle.getElementsByTag("tbody").get(0).getElementsByTag("tr");
			for (Element trEle : trsEle) {
				Elements tds = trEle.children();
				String investorName  = tds.get(0).getElementsByTag("span").get(0).html();
				String type = tds.get(1).html();
				
				
				List<Investor> inves = investorDao.findByName(investorName);
				Investor investor=null;
				if(inves.size()==0){
					Investor in = new Investor();
					in.setName(investorName);
					if("基金".equals(type)){
						in.setType(Investor.TYPE_FUND);
					}else if("保险公司".equals(type)){
						in.setType(Investor.TYPE_SAFE);
					}else if("一般法人".equals(type)){
						in.setType(Investor.TYPE_PERSON);
					}else if ("信托公司".equals(type)){
						in.setType(4);
					}else if ("社保基金".equals(type)){
						in.setType(5);
					}else if("QFII".equals(type)){
						in.setType(6);
					}else if("券商".equals(type)){
						in.setType(7);
					}else if("券商集合理财".equals(type)){
						in.setType(8);
					}else if("企业年金".equals(type)){
						in.setType(9);
					}else{
						in.setType(10);
					}
					in.setId(UUID.uuid32());
					in.setzCreatTime(new Date());
					investor = investorDao.save(in);
				}else{
					investor=inves.get(0);
				}
				
				StockCodeInvestor codeIn = new StockCodeInvestor();
				String num = tds.get(2).html().replace("亿", "");
				Double codeQun =0.0;
				if(num.indexOf("万")!=-1){
					codeQun =Double.parseDouble(num.replace("万", ""));
				}else if(num.indexOf("亿")!=-1){
					codeQun =Double.parseDouble(num.replace("亿", ""))*10000;
				}else if(num.indexOf("-")!=-1){
					codeQun =0.0;
				}else{
					try {
						codeQun = Double.parseDouble(num)/10000;
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
				String percent = tds.get(4).html().replace("%", "");
				if(percent.indexOf("不足")!=-1){
					percent="0.001";
				}
				String commit = tds.get(5).html().replace("<s></s>", "")
						                         .replace("<s class=\"up\"></s>", "增持").replace("<span class=\"upcolor\">", "")
						                         .replace("<s class=\"down\"></s>", "减持").replace("<span class=\"downcolor\">-", "")
						                         .replace("</span>", "").trim();
				codeIn.setId(UUID.uuid32());
				codeIn.setPublicDate(pubDate);
				codeIn.setCodePercent(Double.parseDouble(percent));
				codeIn.setCodeQuantity(codeQun);
				codeIn.setComment(commit);
				codeIn.setSpliderInvestorId(investor.getId());
				codeIn.setSpliderStockCodeId(codeId);
				saveList.add(codeIn);
			}
			
			
			if(haveSplider.size()!=0){
				//如果是当前最新季度，则对比list 和数据库数据是否一致，把网页新增的放到数据里
				List<String> keys= new ArrayList<>();
				for (StockCodeInvestor investor : haveSplider) {
					keys.add(investor.getSpliderInvestorId());
				}
				List<StockCodeInvestor> remove = new ArrayList<>();
				for (StockCodeInvestor save : saveList) {
					if(keys.contains(save.getSpliderInvestorId())){
						remove.add(save);
					}
				}
				saveList.removeAll(remove);
				
			}
			if(saveList.size()>0){
				System.out.println("正在写入文件....");
				OutputWriterUtils.outPut(saveList);
				System.out.println("写入完成");
			}else{
				System.out.println("无需写入-->"+pubDate);
			}
			
			
			
		}
		
	}
	
}
