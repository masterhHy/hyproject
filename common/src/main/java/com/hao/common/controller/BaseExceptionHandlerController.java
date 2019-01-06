package com.hao.common.controller;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hao.common.pojo.ResponseData;


/**
 * controller 异常统一处理类
 *
 */
public class BaseExceptionHandlerController {
	private Logger logger = LoggerFactory.getLogger(BaseExceptionHandlerController.class);

	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ResponseData handler(Exception e){
		ResponseData res = new ResponseData();
		res.setCode(ResponseData.ERROR_CODE);
		res.setStatus(false);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		res.setMessage(sw.toString());
		logger.error("",e);
		return  res;
	}
	
	
	
}
