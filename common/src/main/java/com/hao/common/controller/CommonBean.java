package com.hao.common.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;

public class CommonBean {

    @Bean
    public ObjectMapper getObjectMapper(){
        return new ObjectMapper().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}
