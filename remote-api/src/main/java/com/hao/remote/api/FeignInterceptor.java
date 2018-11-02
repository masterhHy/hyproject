package com.hao.remote.api;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

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
        requestTemplate.header("msClientId", "feigngetdata");
    }
}
