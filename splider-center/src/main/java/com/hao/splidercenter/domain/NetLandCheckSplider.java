package com.hao.splidercenter.domain;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.hao.splidercenter.utils.splider.Splider;
import com.hao.splidercenter.utils.splider.handler.JsonHandler;
@Component
public class NetLandCheckSplider implements SpliderTask {

	@Override
	public void run() {
		//获取token
		NetLandHandler handler = new NetLandHandler();
		Splider s= new Splider(handler);
		String url="";
		Map<String,Object> params = new HashMap<>();
		params.put("appleId", "7C3F9DF3-48A5-4B28-8300-07A216FB0028");
		params.put("companyNo", "51001");
		params.put("mobile", "13928985360");
		params.put("password", "12345678");
		params.put("mobileType", "2");
		params.put("registrationId", "1517bfd3f7e1b67783e");
		url ="http://101.132.78.92:8080/longstron-company-app-rest/api/employeeInfo/login";
		s.readUrl(url, "get", params);
		String token = handler.token;
		System.out.println("当前账户token=========》"+token);
		
		//token="f96f391990aa1dafc67aa9db66612bf9";
		params.clear();
		params.put("address", "广州市天河区中山大道建工路15号首层");
		params.put("isAuto", 2);
		params.put("latitude", "23.1291279654196");
		params.put("longitude", "113.3795327359311");
		params.put("token", token);
		params.put("type", 1);
		params.put("wifi", "netland-office");
		url = "http://101.132.78.92:8080/longstron-company-app-rest/api/workday/add";
		s.readUrl(url, "post", params);
				
		
	}
	
	
	
	
}
class NetLandHandler extends JsonHandler{
	
	public String token;
	@Override
	public void webData(JSONObject json) {
		System.out.println(json);
		if(json.get("data")!=null){
			JSONObject data = (JSONObject)json.get("data");
			if(data.get("token")!=null){
				token = data.get("token").toString();
			}
		}
	}
	@Override
	public void endToDo() {}
	
}
