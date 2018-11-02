package com.hao.splidercenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@EnableAsync
public class SpliderCenterApplication {
   public static void main(String[] args){
	   SpringApplication.run(SpliderCenterApplication.class, args);
   }
   
}
