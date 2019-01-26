package com.hao.authcenter.auth;

import com.hao.authcenter.remote.UserServiceClient;
import com.hao.common.constant.DataBaseConstant;
import com.hao.common.entity.user.SysAuthority;
import com.hao.common.entity.user.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * springSecurity 用到的实现基类
 */
public abstract class BaseUserDetailService implements UserDetailsService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserServiceClient userService;
    
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	SysUser sysUser = this.loadUser(username);
    	
        if(sysUser == null){
            logger.error("找不到该用户，用户名：" + username);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + username);
            
        }

        List<SysAuthority> authList = userService.getAllAuthorityByUserId(sysUser.getId()).getData();
        List<GrantedAuthority> authorities = this.convertToAuthorities(authList);
       
        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User user =  new org.springframework.security.core.userdetails.User(sysUser.getUsername(),
        		sysUser.getPassword(), isActive(sysUser.getIsEnable()), true, true, true, authorities);

        //给权限登录中心用的
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        session.setAttribute("online-user",sysUser);

        return new BaseUserDetail(sysUser, user);

    }
    
    protected abstract SysUser loadUser(String key) throws UsernameNotFoundException;

    private boolean isActive(String active){
        return "Y".equals(active) ? true : false;
    }

    private List<GrantedAuthority> convertToAuthorities(List<SysAuthority> auth) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        auth.forEach(e -> {
            if(e.getUrl()!=null){
                GrantedAuthority authority = new SimpleGrantedAuthority(e.getCode());
                authorities.add(authority);
            }
        });
        return authorities;
    }
}
