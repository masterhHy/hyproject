package com.hao.authcenter.auth.ohterlogin.wx;


import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * 手机验证码登陆 
 */
public class WxAuthenticationProvider  implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		WxAuthenticationToken authenticationToken = (WxAuthenticationToken) authentication;
		//调用自定义的userDetailsService认证
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        
        
        if (user == null) {
            throw new InternalAuthenticationServiceException("微信授权失败");
        }
        //如果user不为空重新构建PhoneAuthenticationToken（已认证）
        WxAuthenticationToken authenticationResult = new WxAuthenticationToken(user, user.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return WxAuthenticationToken.class.isAssignableFrom(authentication);
	}

	public UserDetailsService getUserDetailsService() {
		return userDetailsService;
	}

	public void setUserDetailsService(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	
}
