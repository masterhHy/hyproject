package com.hao.remote.api;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Configuration;

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
