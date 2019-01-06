package com.hao.authcenter.web;

import com.hao.common.controller.BaseExceptionHandlerController;
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
public class ExceptionHandlerController extends BaseExceptionHandlerController {

}
