package com.hao.user.service.impl;

import com.hao.common.entity.user.SysRole;
import com.hao.user.dao.SysRoleMapper;
import com.hao.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
