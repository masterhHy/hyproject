package com.hao.authcenter.auth.ohterlogin.phone;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 手机验证码token
 */
public class PhoneAuthenticationToken  extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 2383092775910246006L;

    /**
     * 手机号
     */
    private final Object principal;
    /**
     * 手机号
     */
    private final Object credentials;

    /**
     * PhoneLoginAuthenticationFilter中构建的未认证的Authentication
     * @param mobile
     */
    public PhoneAuthenticationToken(String mobile,String verifyCode) {
        super(null);
        this.principal = mobile;
        this.credentials = verifyCode;
        
        setAuthenticated(false);
    }

    /**
     * PhoneAuthenticationProvider中构建已认证的Authentication
     * @param principal
     * @param authorities
     */
    public PhoneAuthenticationToken(Object principal,Object credentials,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    /**
     * @param isAuthenticated
     * @throws IllegalArgumentException
     */
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException(
                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        }

        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
    }
}
