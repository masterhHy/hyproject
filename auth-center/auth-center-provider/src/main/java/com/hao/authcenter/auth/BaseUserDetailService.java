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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * springSecurity 用到的实现类
 */
@Service
public class BaseUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserServiceClient userService;
    
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 调用FeignClient查询用户
        SysUser sysUser = userService.getUserByUsername(username).getData();

        if(sysUser == null){
            logger.error("找不到该用户，用户名：" + username);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + username);
            
        }

        List<SysAuthority> authList = userService.getAllAuthorityByUserId(sysUser.getId()).getData();
        List<GrantedAuthority> authorities = this.convertToAuthorities(sysUser, authList);
       
        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User user =  new org.springframework.security.core.userdetails.User(sysUser.getUsername(),
        		sysUser.getPassword(), isActive(sysUser.getIsEnable()), true, true, true, authorities);
        redisTemplate.opsForValue().set(DataBaseConstant.REDIS_USER_NAME_PLACE+sysUser.getId()+"-user",sysUser);
        return new BaseUserDetail(sysUser, user);

    }

    private boolean isActive(String active){
        return "Y".equals(active) ? true : false;
    }

    private List<GrantedAuthority> convertToAuthorities(SysUser sysUser,List<SysAuthority> auth) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 清除 Redis 中用户的权限
        redisTemplate.delete(DataBaseConstant.REDIS_USER_NAME_PLACE+sysUser.getId()+"-menu");
        auth.forEach(e -> {

            GrantedAuthority authority = new SimpleGrantedAuthority(e.getCode());
            authorities.add(authority);

            //存储权限到redis
            redisTemplate.opsForList().rightPush(DataBaseConstant.REDIS_USER_NAME_PLACE+sysUser.getId()+"-menu", e);
            
        });
        return authorities;
    }
}
