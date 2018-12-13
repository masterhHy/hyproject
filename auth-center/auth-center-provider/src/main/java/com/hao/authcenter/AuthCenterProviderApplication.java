package com.hao.authcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringCloudApplication
@EnableFeignClients
public class AuthCenterProviderApplication {
   public static void main(String[] args){
	   SpringApplication.run(AuthCenterProviderApplication.class, args);
   }
}
