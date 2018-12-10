package com.hao.user.service.impl;

import com.hao.common.entity.user.*;
import com.hao.user.dao.*;
import com.hao.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {

	@Autowired
	private SysUserMapper userMapper;

	

	@PostConstruct
	public void initSysUser() {
		SysUser admin = new SysUser();
		admin.setId("1");
		admin =userMapper.selectOne(admin);
		if(admin==null){
			admin = new SysUser();
			admin.setCreatedDate(new Date());
			admin.setFirstName("管理员");
			admin.setId("1");
			admin.setIsEnable("Y");
			admin.setSex(0);
			admin.setUsername("admin");
			admin.setPhone("10086");
			BCryptPasswordEncoder bc = new BCryptPasswordEncoder(6);
			admin.setPassword(bc.encode("84992361"));
			userMapper.insertSelective(admin);
		}

		
	}

	


}
