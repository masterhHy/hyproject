package com.hao.user.service;


import com.hao.common.entity.user.SysRole;

import java.util.List;

public interface RoleService extends BaseService<SysRole> {

	public List<SysRole> getRoleByUserId(String userId);
}
