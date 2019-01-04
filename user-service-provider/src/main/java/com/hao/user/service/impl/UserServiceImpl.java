package com.hao.user.service.impl;

import com.hao.common.entity.user.SysUser;
import com.hao.common.entity.user.SysUserRoles;
import com.hao.common.utils.UUID;
import com.hao.user.dao.SysUserMapper;
import com.hao.user.dao.SysUserRolesMapper;
import com.hao.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.util.Date;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {

	@Autowired
	private SysUserMapper userMapper;

	@Autowired
	private SysUserRolesMapper sysUserRolesMapper;

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

	public void registUser(SysUser user) {
		//注册用户
		if(StringUtils.isBlank(user.getId())){
			user.setId(UUID.uuid32());
		}
		user.setCreatedDate(new Date());
		user.setIsEnable("Y");
		user.setPhone(user.getUsername());
		user.setFirstName(UUID.dealPhone(user.getUsername()));
		userMapper.insertSelective(user);
		//添加基础角色
		SysUserRoles ur = new SysUserRoles();
		ur.setId(UUID.uuid32());
		ur.setRolesId("1");
		ur.setSysUserId(user.getId());
		sysUserRolesMapper.insert(ur);

	}


}
