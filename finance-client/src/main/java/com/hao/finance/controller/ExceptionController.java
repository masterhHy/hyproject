package com.hao.finance.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

	
	@ExceptionHandler({Exception.class})
	public void handler(HttpServletRequest request ,Exception e){
		System.out.println("-----------------------------进入这里-------------------------------");
		System.out.println("-----------------------------进入这里-------------------------------");
		System.out.println("-----------------------------进入这里-------------------------------");
		System.out.println("-----------------------------进入这里-------------------------------");
	}


	
	
	
}
