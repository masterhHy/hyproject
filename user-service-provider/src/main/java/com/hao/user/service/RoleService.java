package com.hao.user.service;

import java.util.List;

import com.hao.user.entity.SysRole;

public interface RoleService extends BaseService<SysRole> {

	public List<SysRole> getRoleByUserId(String userId);
}
