package com.hao.finance.controller;

import javax.servlet.http.HttpServletRequest;

import com.hao.common.pojo.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
	private Logger logger = LoggerFactory.getLogger(ExceptionController.class);
	
	@ExceptionHandler({Exception.class})
	public ResponseData handler(ResponseData res , Exception e){
		res.setMessage(e.getMessage());
		res.setCode(500);
		logger.error("",e);
		return  res;
	}


	
	
	
}
