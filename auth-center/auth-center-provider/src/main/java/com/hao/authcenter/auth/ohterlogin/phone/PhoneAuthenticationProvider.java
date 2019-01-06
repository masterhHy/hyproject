package com.hao.authcenter.auth.ohterlogin.phone;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

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
