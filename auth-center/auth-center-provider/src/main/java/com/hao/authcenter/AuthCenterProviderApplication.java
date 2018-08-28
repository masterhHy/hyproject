package com.hao.authcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;


@SpringBootApplication
@EnableAuthorizationServer
@ImportResource({ "classpath:dubbo/*.xml" }) 
public class AuthCenterProviderApplication {
   public static void main(String[] args){
	   SpringApplication.run(AuthCenterProviderApplication.class, args);
   }
}
