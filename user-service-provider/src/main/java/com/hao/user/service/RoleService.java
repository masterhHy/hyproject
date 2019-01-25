package com.hao.user.service;


import com.hao.common.entity.user.SysRole;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysRoleQuery;

import java.util.List;

public interface RoleService extends BaseService<SysRole> {

	public List<SysRole> getRoleByUserId(String userId);

	public TableData<SysRole> getRoleData(SysRoleQuery query);

	public void initSysRole();
}
