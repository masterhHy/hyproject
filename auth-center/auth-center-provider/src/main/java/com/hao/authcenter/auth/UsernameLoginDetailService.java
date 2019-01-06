package com.hao.authcenter.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.authcenter.remote.UserServiceClient;
import com.hao.common.entity.user.SysUser;
/**
 * security 账号密码登录 处理类 
 * @author Administrator
 *
 */
@Service
public class UsernameLoginDetailService extends BaseUserDetailService {

	@Autowired
    private UserServiceClient userService;
	
	protected SysUser loadUser(String key){
		// 调用FeignClient查询用户
		SysUser sysUser = userService.getUserByUsername(key).getData();
		return sysUser;
	}
	
}
