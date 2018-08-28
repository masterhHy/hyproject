package com.hao.user.service.impl;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hao.user.dao.SysRoleMapper;
import com.hao.user.entity.SysRole;
import com.hao.user.service.RoleService;

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
