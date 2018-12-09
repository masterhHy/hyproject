package com.hao.time;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringCloudApplication
@EnableFeignClients
public class TimeCoinClientApplication {

   public static void main(String[] args){
	   SpringApplication.run(TimeCoinClientApplication.class, args);
   }
}
