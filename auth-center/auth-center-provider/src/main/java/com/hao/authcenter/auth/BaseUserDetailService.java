package com.hao.authcenter.auth;

import java.util.ArrayList;
import java.util.List;

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

import com.hao.common.pojo.ResponseData;
import com.hao.common.utils.UUID;
import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysRole;
import com.hao.user.entity.SysUser;
import com.hao.user.service.ResourceService;
import com.hao.user.service.RoleService;
import com.hao.user.service.UserService;


/**
 * springSecurity 用到的实现类
 */
@Service
public class BaseUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RedisTemplate<String, SysRole> redisTemplate;
    @Autowired
    private RedisTemplate<String, SysAuthority> resourcesTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	SysUser record = new SysUser();
    	record.setUsername(username);
    	SysUser sysUser = userService.selectOne(record);
        // 调用FeignClient查询用户
        if(sysUser == null){
            logger.error("找不到该用户，用户名：" + username);
            throw new UsernameNotFoundException("找不到该用户，用户名：" + username);
            
        }
        // 查询用户所有角色
        List<SysRole> roles = roleService.getRoleByUserId(sysUser.getId());
        List<GrantedAuthority> authorities = this.convertToAuthorities(sysUser, roles);
       
        // 返回带有用户权限信息的User
        org.springframework.security.core.userdetails.User user =  new org.springframework.security.core.userdetails.User(sysUser.getUsername(),
        		sysUser.getPassword(), isActive(sysUser.getIsEnable()), true, true, true, authorities);
        return new BaseUserDetail(sysUser, user);

      /*  

        //调用FeignClient查询菜单
        ResponseData<List<BaseModuleResources>> baseModuleResourceListResponseData = baseModuleResourceService.getMenusByUserId(baseUser.getId());

        // 获取用户权限列表
        List<GrantedAuthority> authorities = convertToAuthorities(baseUser, roles);

        // 存储菜单到redis
        if( ResponseCode.SUCCESS.getCode().equals(baseModuleResourceListResponseData.getCode()) && baseModuleResourceListResponseData.getData() != null){
            resourcesTemplate.delete(baseUser.getId() + "-menu");
            baseModuleResourceListResponseData.getData().forEach(e -> {
                resourcesTemplate.opsForList().leftPush(baseUser.getId() + "-menu", e);
            });
        }

        */
    }

    private boolean isActive(String active){
        return "Y".equals(active) ? true : false;
    }

    private List<GrantedAuthority> convertToAuthorities(SysUser sysUser, List<SysRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        // 清除 Redis 中用户的角色
        redisTemplate.delete(sysUser.getId());
        roles.forEach(e -> {
            // 存储用户、角色信息到GrantedAuthority，并放到GrantedAuthority列表
            GrantedAuthority authority = new SimpleGrantedAuthority(e.getCode());
            authorities.add(authority);
            //存储角色到redis
            redisTemplate.opsForList().rightPush(sysUser.getId(), e);
            
        });
        return authorities;
    }
}
