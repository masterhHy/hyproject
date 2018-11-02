package com.hao.user.service;

import com.hao.user.entity.SysRole;

import java.util.List;

public interface RoleService extends BaseService<SysRole> {

	public List<SysRole> getRoleByUserId(String userId);
}
