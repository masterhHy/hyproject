package com.hao.finance.splider.utils.splider.handler;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;

import java.io.StringReader;

public abstract class JsonHandler extends ErrorHandler implements ObjectHandler {

	@Override
	public void webData(String data) {
		JSONReader reader = new JSONReader(new StringReader(data));
		if(data.startsWith("[")){
			reader.startArray();
			while(reader.hasNext()){
				JSONObject json = new JSONObject();
				reader.readObject(json);
				this.webData(json);
			}
			reader.endArray();
		}else if(data.startsWith("{")){
			reader.startObject();
			JSONObject json = new JSONObject();
			while(reader.hasNext()) {
				String key = reader.readString();
				Object val = reader.readObject();
				json.put(key, val);
			}
			this.webData(json);
			reader.endObject();
		}else{
			throw new RuntimeException("不是json格式");
		}
		this.endToDo();
	}
	
	public abstract void webData(JSONObject json);
	
	public abstract void endToDo();
	

}
