package com.hao.authcenter.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class ControllerAOP {


    /**
     * 修改 /oauth/authorize 返回值，添加多一个参数
     * @param joinPoint
     * @param result
     */
    @AfterReturning(value = "execution(* org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.authorize(..))",returning = "result")
    public void addParams(JoinPoint joinPoint,Object result){
        ModelAndView view = (ModelAndView) result;
        RedirectView rv = (RedirectView) view.getView();
        if(rv!=null){
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String sessionId = request.getSession().getId();
            sessionId="code";
            String path=rv.getUrl()+"&unique_code="+sessionId;
            view.setViewName("redirect:"+path);
        }
    }
    @AfterReturning(value = "execution(* org.springframework.security.oauth2.provider.endpoint.AuthorizationEndpoint.approveOrDeny(..))",returning = "result")
    public void addParamsApproveOrDeny(JoinPoint joinPoint,Object result){
        RedirectView rv = (RedirectView) result;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String sessionId = request.getSession().getId();
        sessionId="code";
        String path=rv.getUrl()+"&unique_code="+sessionId;
        rv.setUrl(path);
    }
}
