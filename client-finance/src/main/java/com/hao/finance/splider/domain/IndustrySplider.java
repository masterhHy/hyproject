package com.hao.finance.splider.domain;

import com.hao.common.utils.UUID;
import com.hao.finance.splider.dao.DictionaryDao;
import com.hao.finance.splider.dao.StockCodeDao;
import com.hao.finance.splider.po.Dictionary;
import com.hao.finance.splider.po.StockCode;
import com.hao.finance.splider.utils.splider.handler.HtmlHandler;
import com.hao.finance.splider.utils.splider.thread.HttpConnectionManager;
import com.hao.finance.splider.utils.splider.thread.Monitor;
import com.hao.finance.splider.utils.splider.thread.SpliderTaskVo;
import com.hao.finance.splider.utils.splider.thread.TaskRun;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class IndustrySplider implements SpliderTask {
	@Autowired
	private StockCodeDao stockCodeDao;
	@Autowired
	private DictionaryDao dictionaryDao;
	@Autowired
	private HttpConnectionManager pools;
	
	Logger logger = LoggerFactory.getLogger(IndustrySplider.class);
	
	@Override
	public void run() {
		List<Dictionary> p = dictionaryDao.findBySortAndParentIdIsNull(Dictionary.SORT_INDUSTRY);
		Dictionary parent =null;
		if(p.size()==0){
			Dictionary dic = new Dictionary();
			dic.setId(UUID.uuid32());
			dic.setCode(Dictionary.SORT_INDUSTRY+"_1");
			dic.setName("行业");
			dic.setSort(Dictionary.SORT_INDUSTRY);
			dic.setzCreatTime(new Date());
			dictionaryDao.save(dic);
			dictionaryDao.flush();
			parent=dic;
		}else{
			parent=p.get(0);
		}
		List<StockCode> codeList = stockCodeDao.findByDitionaryCodeIsNull();
		
		List<SpliderTaskVo> list = new ArrayList<>();
		for (StockCode stockCode : codeList) {
			IndustryHandler handler = new IndustryHandler(stockCodeDao,dictionaryDao,stockCode.getId(),parent);
			String url ="http://basic.10jqka.com.cn/"+stockCode.getCode()+"/company.html#stockpage";
			
			SpliderTaskVo v = new SpliderTaskVo();
			v.setUrl(url);
			v.setHandler(handler);
			v.setType("get");
			v.setParams(null);
			list.add(v);
		}
		IndustryMonitor m = new IndustryMonitor();
		TaskRun.run(list,m,pools);
	}
	
	

}

class IndustryMonitor implements Monitor {

	@Override
	public void currendURL(String url) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void end(List<SpliderTaskVo> error) {
		// TODO Auto-generated method stub
		
	}
	


	
	
}

class IndustryHandler extends HtmlHandler {
	private StockCodeDao stockCodeDao;
	private DictionaryDao dictionaryDao;
	private String codeId;
	private Dictionary parent;
	public IndustryHandler(StockCodeDao stockCodeDao, DictionaryDao dictionaryDao, String codeId, Dictionary parent) {
		this.stockCodeDao=stockCodeDao;
		this.dictionaryDao=dictionaryDao;
		this.codeId=codeId;
		this.parent=parent;
		
	}
	
	@Override
	public void webData(Document doc) {
		Element tableEle = doc.getElementById("detail");
		Element trEle = tableEle.getElementsByClass("m_table").get(0).getElementsByTag("tr").get(1);
		String text = trEle.getElementsByTag("td").get(1).getElementsByTag("span").text();
		if(text.indexOf("—")!=-1){
			String name = text.split("—")[1].trim();
			Dictionary dic = null;
			List<Dictionary> list = dictionaryDao.findByNameAndSort(name, Dictionary.SORT_INDUSTRY);
			if(list.size()>0){
				dic=list.get(0);
			}else{
				dic = new Dictionary();
				dic.setId(UUID.uuid32());
				String code =Dictionary.SORT_INDUSTRY+"_"+(dictionaryDao.getMaxIndexBySort(Dictionary.SORT_INDUSTRY)+1);
				dic.setCode(code);
				dic.setName(name);
				dic.setSort(Dictionary.SORT_INDUSTRY);
				dic.setParentId(parent.getId());
				dic.setzCreatTime(new Date());
				dic =dictionaryDao.save(dic);
				dictionaryDao.flush();
			}
			StockCode stockCode = stockCodeDao.findById(codeId).get();
			stockCode.setDitionaryCode(dic.getCode());
			stockCode.setzUpdateTime(new Date());
			stockCodeDao.save(stockCode);
		}
		
	}
	
	
}
