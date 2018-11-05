package com.hao.user.service.impl;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hao.user.dao.SysAuthorityMapper;
import com.hao.user.dao.SysRoleAuthoritiesMapper;
import com.hao.user.dao.SysRoleMapper;
import com.hao.user.dao.SysUserMapper;
import com.hao.user.dao.SysUserRolesMapper;
import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysRole;
import com.hao.user.entity.SysRoleAuthorities;
import com.hao.user.entity.SysUser;
import com.hao.user.entity.SysUserRoles;
import com.hao.user.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {

	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Autowired
	private SysUserRolesMapper sysUserRolesMapper;
	@Autowired
	private SysAuthorityMapper sysAuthorityMapper;
	@Autowired
	private SysRoleAuthoritiesMapper sysRoleAuthoritiesMapper;
	
	

	@PostConstruct
	public void initSysUser() {
		SysUser admin = new SysUser();
		admin.setId("1");
		admin =userMapper.selectOne(admin);
		SysRole role = new SysRole();
		role.setId("1");
		role = sysRoleMapper.selectOne(role);
		SysAuthority auth = new SysAuthority();
		auth.setId("1");
		auth = sysAuthorityMapper.selectOne(auth);
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
		if(role==null){
			role = new SysRole();
			role.setCode("BASE");
			role.setCreatedDate(new Date());
			role.setId("1");
			role.setIsEnable("Y");
			role.setName("基础权限");
			sysRoleMapper.insertSelective(role);
			SysUserRoles ur = new SysUserRoles();
			ur.setId("1");
			ur.setRolesId("1");
			ur.setSysUserId("1");
			sysUserRolesMapper.insertSelective(ur);
		}
		if(auth==null){
			auth = new SysAuthority();
			auth.setCode("BASE");
			auth.setId("1");
			auth.setIsEnable("Y");
			auth.setCreatedDate(new Date());
			auth.setName("基础权限");
			auth.setUrl("/");
			auth.setProjectName("ALL");
			sysAuthorityMapper.insertSelective(auth);
			SysRoleAuthorities ra = new SysRoleAuthorities();
			ra.setAuthoritiesId("1");
			ra.setId("1");
			ra.setSysRoleId("1");
			sysRoleAuthoritiesMapper.insertSelective(ra);
		}
			
		
	}

	


}
