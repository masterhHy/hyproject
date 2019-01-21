package com.hao.api.gateway;

import com.alibaba.fastjson.JSONObject;
import com.hao.common.entity.user.SysUser;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;

@Component
public class AccessFilter extends ZuulFilter {
 
    private static Logger log = LoggerFactory.getLogger(AccessFilter.class);
 
    @Override
    public String filterType() {
        //前置过滤器
        return "pre";
    }
 
    @Override
    public int filterOrder() {
        //优先级，数字越大，优先级越低
        return 0;
    }
 
    @Override
    public boolean shouldFilter() {
        //是否执行该过滤器，true代表需要过滤
        return true;
    }
 
    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        //把用户用户信息放到转发请求中， 方便微服务获取用户信息
        SysUser user = (SysUser) request.getAttribute("user_info");
        //把页面请求的对象 的头部信息放到 网关 请求头上
        try {
            ctx.getZuulRequestHeaders().put("user_info",URLEncoder.encode(JSONObject.toJSONString(user), "UTF-8"));
        }catch (Exception e){
            log.error("",e);
        }

        log.info("send {} request to {}", request.getMethod(), request.getRequestURL().toString());
 
        //这里return的值没有意义，zuul框架没有使用该返回值
        return null;
    }
 
}

