package com.hao.api.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/*
 * 资源网关，为资源服务提供路由 不能用在权限中心
 */
@EnableZuulProxy
@EnableResourceServer
@SpringCloudApplication
public class ApiGatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
