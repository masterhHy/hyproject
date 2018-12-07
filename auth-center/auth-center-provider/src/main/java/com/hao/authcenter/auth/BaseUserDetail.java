package com.hao.authcenter.auth;

import com.hao.common.entity.user.SysUser;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


/**
 * springSecurity 用到的实现类
 * 包装org.springframework.security.core.userdetails.User类
 */
public class BaseUserDetail implements UserDetails, CredentialsContainer {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final SysUser sysUser;
    private final org.springframework.security.core.userdetails.User user;

    public BaseUserDetail(SysUser sysUser, User user) {
        this.sysUser = sysUser;
        this.user = user;
    }


    @Override
    public void eraseCredentials() {
        user.eraseCredentials();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return user.getAuthorities();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return user.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return user.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return user.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return user.isEnabled();
    }

    public SysUser getBaseUser() {
        return sysUser;
    }
}
