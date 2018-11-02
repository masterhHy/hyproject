package com.hao.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;


@SpringBootApplication
@EnableResourceServer
public class UserCenterApplication {
   public static void main(String[] args){
	   SpringApplication.run(UserCenterApplication.class, args);
   }
   

}
