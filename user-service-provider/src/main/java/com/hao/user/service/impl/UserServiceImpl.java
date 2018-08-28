package com.hao.user.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.user.dao.SysUserMapper;
import com.hao.user.entity.SysUser;
import com.hao.user.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {

	@Autowired
	private SysUserMapper userMapper;

	


}
