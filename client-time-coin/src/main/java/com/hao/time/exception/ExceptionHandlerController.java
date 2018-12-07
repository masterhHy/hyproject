package com.hao.time.exception;

import com.hao.common.pojo.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * controller 异常统一处理类
 *
 */
@ControllerAdvice
public class ExceptionHandlerController {
	private Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

	@ExceptionHandler({Exception.class})
	@ResponseBody
	public ResponseData handler(Exception e){
		ResponseData res = new ResponseData();
		res.setCode(ResponseData.ERROR_CODE);
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		res.setMessage(sw.toString());
		logger.error("",e);
		return  res;
	}
	
	
	
}
