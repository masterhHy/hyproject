package com.hao.user.service.impl;

import com.hao.common.entity.user.SysRole;
import com.hao.user.dao.SysRoleMapper;
import com.hao.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl extends BaseServiceImpl<SysRole> implements RoleService {

	@Autowired
	private SysRoleMapper sysRoleMapper;
	@Override
	public List<SysRole> getRoleByUserId(String userId) {
		// TODO Auto-generated method stub
		return sysRoleMapper.getRoleByUserId(userId);
	}
	@PostConstruct
	public void initSysRole() {
		SysRole baseRole = new SysRole();
		baseRole.setId("1");
		baseRole =sysRoleMapper.selectOne(baseRole);
		if(baseRole==null){
			baseRole = new SysRole();
			baseRole.setCreatedDate(new Date());
			baseRole.setId("1");
			baseRole.setIsEnable("Y");
			baseRole.setName("基础角色");
			baseRole.setCode("BASE_ROLE");
			baseRole.setCreatedBy("admin");
			sysRoleMapper.insertSelective(baseRole);
		}
	}


}
