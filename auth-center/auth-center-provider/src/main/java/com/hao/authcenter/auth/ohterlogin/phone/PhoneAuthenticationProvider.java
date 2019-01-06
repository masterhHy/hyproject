package com.hao.authcenter.auth.ohterlogin.phone;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.hao.common.pojo.ResponseData;

/**
 * 手机验证码登陆 
 */
public class PhoneAuthenticationProvider  implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		PhoneAuthenticationToken authenticationToken = (PhoneAuthenticationToken) authentication;
		
		 //调用自定义的userDetailsService认证
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        
        
        if (user == null) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }else{
        	String phone=authenticationToken.getPrincipal().toString();
        	String code=authenticationToken.getCredentials().toString();
        	String module=authenticationToken.getModule();
        	
        	HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession session = request.getSession();
            Date time = (Date) session.getAttribute(authentication.getPrincipal()+code+module);
            Boolean isSuccess=false;
            String msg = "";
            if(time==null){
                msg="验证码错误";
            }else if(System.currentTimeMillis()>time.getTime()){
            	msg="验证码已过期";
            }else{
            	isSuccess=true;
                session.removeAttribute(phone+code+module);
            }
            if(!isSuccess){
            	throw new InternalAuthenticationServiceException(msg);
            }
        }
        //如果user不为空重新构建PhoneAuthenticationToken（已认证）
        PhoneAuthenticationToken authenticationResult = new PhoneAuthenticationToken(user,authenticationToken.getCredentials(), user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return PhoneAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	
}
