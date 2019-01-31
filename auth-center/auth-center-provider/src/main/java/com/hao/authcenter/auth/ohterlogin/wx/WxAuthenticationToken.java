package com.hao.authcenter.auth.ohterlogin.wx;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 微信code token
 */
public class WxAuthenticationToken  extends AbstractAuthenticationToken {

	private static final long serialVersionUID = 2383092775910246006L;

    /**
     * 微信code
     */
    private final Object principal;
    

    /**
     * WxLoginAuthenticationFilter中构建的未认证的Authentication
     * @param mobile
     */
    public WxAuthenticationToken(String code) {
        super(null);
        this.principal = code;
        
        setAuthenticated(false);
    }

    /**
     * WxAuthenticationProvider中构建已认证的Authentication
     * @param principal
     * @param authorities
     */
    public WxAuthenticationToken(Object code,
                                      Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = code;
        super.setAuthenticated(true); // must use super, as we override
    }

    @Override
    public Object getCredentials() {
        return null;
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
