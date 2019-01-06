package com.hao.authcenter.auth.ohterlogin.phone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.authcenter.auth.BaseUserDetailService;
import com.hao.authcenter.remote.UserServiceClient;
import com.hao.common.entity.user.SysUser;


@Service
public class PhoneLoginDetailService extends BaseUserDetailService {

	@Autowired
    private UserServiceClient userService;
	
	protected SysUser loadUser(String key){
		// 调用FeignClient查询用户
		SysUser sysUser = userService.getUserByUsername(key).getData();
		return sysUser;
	}

}
