package com.hao.authcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


@SpringBootApplication
@EnableAuthorizationServer
@EnableFeignClients
public class AuthCenterProviderApplication {
   public static void main(String[] args){
	   SpringApplication.run(AuthCenterProviderApplication.class, args);
   }
}
