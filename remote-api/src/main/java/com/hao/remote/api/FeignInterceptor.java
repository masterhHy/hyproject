package com.hao.remote.api;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/*
 * 这项目主要存放各个微服务对外开放的接口
 */

/**
 * feign拦截器 给后台应用请求加上head 方便权限控制放开
 */
@Configuration
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        Enumeration<String> headers =  request.getHeaderNames();
        //把页面请求的对象 的头部信息放到 feign 请求头上
        while(headers.hasMoreElements()){
            String key = headers.nextElement();
            String value = request.getHeader(key);
            requestTemplate.header(key, value);
        }

    }
}
