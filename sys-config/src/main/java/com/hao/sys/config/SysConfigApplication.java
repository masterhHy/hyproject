package com.hao.sys.config;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringCloudApplication
@EnableConfigServer
public class SysConfigApplication {
    public static void main(String[] args){
        SpringApplication.run(SysConfigApplication.class, args);
    }
}
