package com.hao.splidercenter.utils.splider.thread;

import java.util.LinkedList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public interface Monitor {
	List<SpliderTaskVo> errorList=new LinkedList<>();
	/**
	 * 当前爬去url
	 */
	public void currendURL(String url);
	/**
	 * 
	 * @param error 结束后调用，出错错误的连接结合
	 */
	public void end(List<SpliderTaskVo> error);
	
}
