package com.hao.finance.splider.utils.splider.handler;

import javax.management.RuntimeErrorException;

public abstract class ErrorHandler implements ObjectHandler {
	@Override
	public void webOriginalDataStr(String data) {
		this.dealErrorData(data);
	}
	protected void dealErrorData(String data){
		if(data.indexOf("<title>402 Payment Required</title>")!=-1){
			throw new RuntimeErrorException(new Error("阿里云账号过期"));
		}
		this.webData(data);
	};
	protected abstract void webData(String data);
	
	
}
