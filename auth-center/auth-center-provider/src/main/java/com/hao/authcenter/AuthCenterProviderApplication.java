package com.hao.authcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


@SpringCloudApplication
@EnableAuthorizationServer
@EnableFeignClients
@ServletComponentScan
public class AuthCenterProviderApplication {
   public static void main(String[] args){
	   SpringApplication.run(AuthCenterProviderApplication.class, args);
   }
}
