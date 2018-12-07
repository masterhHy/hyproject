package com.hao.common.controller;

import com.hao.common.entity.user.SysUser;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class BaseSpringController {

    public String getUserId(){
        return this.getUser().getId();
    }
    public SysUser getUser(){
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        SysUser user = (SysUser) request.getAttribute("user_info");
        return user;
    }
}
