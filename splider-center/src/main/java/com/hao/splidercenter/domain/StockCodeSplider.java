package com.hao.splidercenter.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hao.common.utils.UUID;
import com.hao.splidercenter.dao.StockCodeDao;
import com.hao.splidercenter.po.StockCode;
import com.hao.splidercenter.utils.splider.Splider;
import com.hao.splidercenter.utils.splider.handler.JsonHandler;

@Component
public class StockCodeSplider implements SpliderTask {
	@Autowired
	private StockCodeDao stockCodeDao;
	
	@Override
	public void run() {
		Splider s= new Splider(new StonedCodeHandler( stockCodeDao));
		Map<String,Object> param = new HashMap<>();
		param.put("type", "CT");
		param.put("token", "4f1862fc3b5e77c150a2b985b12db0fd");
		param.put("sty", "FCOIATC");
		param.put("cmd", "C._A");
		param.put("st", "(Code)");
		param.put("_", "1533392599878");
		param.put("sr", "1");
		param.put("p", "1");//页码 
		param.put("ps", "10000");//每页显示多少条
		param.put("js", "{\"data\":[(x)]}");//返回格式
		s.readUrl("http://nufm.dfcfw.com/EM_Finance2014NumericApplication/JS.aspx", "get", param);
		
	}

	
	class StonedCodeHandler  extends JsonHandler{
		private StockCodeDao stockCodeDao;
		public StonedCodeHandler(StockCodeDao stockCodeDao) {
			this.stockCodeDao=stockCodeDao;
		}
		@Override
		public void webData(JSONObject json) {
			System.out.println("正在入库");
			List<String> list = (List<String>) json.get("data");
			List<StockCode> entities = new ArrayList<>();
			for (String val : list) {
				String[] split = val.split(",");
				List<StockCode> codeList = stockCodeDao.findByCode(split[1]);
				if(codeList.size()>0){
					continue;
				}
				StockCode code = new StockCode();
				code.setId(UUID.uuid32());
				code.setType(Integer.parseInt(split[0]));
			    code.setCode(split[1]);
			    code.setName(split[2]);
			    entities.add(code);
			}
			stockCodeDao.saveAll(entities);
			System.out.println("入库完成");
			
		}
		@Override
		public void endToDo() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
}
