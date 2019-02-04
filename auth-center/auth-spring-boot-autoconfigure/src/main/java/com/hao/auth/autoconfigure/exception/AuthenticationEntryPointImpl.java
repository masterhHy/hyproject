package com.hao.auth.autoconfigure.exception;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import com.hao.common.pojo.ResponseData;
import com.hao.common.utils.HTTPUtils;

/**
 * 匿名访问保护资源 异常处理器
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        if (HTTPUtils.isAjaxRequest(request)) {// AJAX请求,使用response发送403
            if(e.getMessage().equals("没登录访问保护资源")){
                response.sendError(402,e.getMessage());
            }else if(e.getMessage().equals("修改密码，重新登陆")){
            	 response.sendError(401,e.getMessage());
            }else{
                response.sendError(403,e.getMessage());
            }

        } else{
            if (!response.isCommitted()){
                //应该返回页面 暂时以json返回
                ResponseData<Map<String,Object>> res = new ResponseData<>();
                res.setCode(403);
                res.setMessage(e.getMessage());
                HTTPUtils.outputJSON(response,res);
            }else{
                System.out.println("没处理到异常");
            }

        }

    }
}
