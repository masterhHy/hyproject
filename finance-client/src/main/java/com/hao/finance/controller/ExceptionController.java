package com.hao.finance.controller;


import com.hao.common.pojo.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionController {
	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ResponseData handler( Exception e){
		ResponseData res = new ResponseData();
		res.setMessage(e.getMessage());
		res.setCode(ResponseData.ERROR_CODE);
		logger.error("",e);
		return  res;
	}


	
	
	
}
