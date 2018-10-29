package com.hao.finance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@EnableResourceServer
@SpringBootApplication
public class FinanceClientApplication {
   public static void main(String[] args){
	   SpringApplication.run(FinanceClientApplication.class, args);
   }


}
