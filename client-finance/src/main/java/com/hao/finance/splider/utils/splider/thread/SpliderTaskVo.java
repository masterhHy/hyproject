package com.hao.finance.splider.utils.splider.thread;

import com.hao.finance.splider.utils.splider.handler.ObjectHandler;

import java.util.Map;

public class SpliderTaskVo {
	/**
	 * 爬虫链接
	 */
	private String url;
	/**
	 * 爬虫链接所需要的参数
	 */
	private Map<String,Object> params;
	/**
	 * 链接的类型 post/get
	 */
	private String type;
	
	private String unicode="UTF-8";
	
	/**
	 * 数据获取后回调
	 */
	private ObjectHandler handler;
	
	
	

	public String getUnicode() {
		return unicode;
	}

	public void setUnicode(String unicode) {
		this.unicode = unicode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ObjectHandler getHandler() {
		return handler;
	}

	public void setHandler(ObjectHandler handler) {
		this.handler = handler;
	}

	@Override
	public String toString() {
		return "SpliderTaskVo [url=" + url + ", params=" + params + ", type=" + type + "]";
	}
	
	public String toGetString() {
		String str=url;
		String param="";
		int num=0;
		if(params!=null){
			for (String key : params.keySet()) {
				if(num==0){
					param+=("?"+key+"="+params.get(key));
				}else{
					param+=("&"+key+"="+params.get(key));
				}
				
				num++;
			}
		}
		
		return str+param;
	}
	
	
	
}
