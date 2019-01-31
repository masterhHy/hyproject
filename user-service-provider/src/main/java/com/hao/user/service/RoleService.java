package com.hao.user.service;


import com.hao.common.entity.user.SysRole;
import com.hao.common.entity.user.SysUser;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysRoleQuery;
import com.hao.common.query.user.SysUserQuery;

import java.util.List;
import java.util.Map;

public interface RoleService extends BaseService<SysRole> {

	public List<SysRole> getRoleByUserId(String userId);

	public TableData<Map<String,Object>> getRoleData(SysRoleQuery query);
	
	public void addOrUpdateRole(SysRole role,String auth);
	public void deleteRoleById(String id);

	public void initSysRole();
	
	/**
	 * 获取该角色下的用户 当hisUser 为true 则获取他下面的user 否则不是他的user
	 * @param query
	 * @return
	 */
	public TableData<SysUser> getRoleUserData(SysRoleQuery query);
	
	public void addUserToRole(String roleId,String userIds);
	public void deleteUserToRole(String roleId,String userIds);
}
