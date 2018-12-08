package com.hao.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.hao.common.entity.user.SysUser;

public class BaseSpringController {

    public String getUserId(){
        return this.getUser().getId();
    }
    public SysUser getUser(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String userinfo = request.getHeader("user_info");
        SysUser user = JSONObject.parseObject(userinfo, SysUser.class);
        return user;
    }
}
