package com.hao.user.service;


import com.hao.common.entity.user.SysRole;
import com.hao.common.entity.user.SysUser;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysUserQuery;

public interface UserService extends BaseService<SysUser> {
	
	public void initSysUser();

	public void registUser(SysUser user);
	
	public void refreshRedisUser();
	
	public TableData<SysUser> getUserData(SysUserQuery query);
	
	/**
	 * 获取用户下的所有角色 hisRole true 获取旗下的  false获取不是他下的
	 * @param query
	 * @return
	 */
	public TableData<SysRole> getUserRoleData(SysUserQuery query);
	
	public void deleteUserById(SysUserQuery query);
	public void addOrUpdateUser(SysUser user);
	public void addRoleoUser(SysUserQuery query);
	public void deleteRoleFromUser(SysUserQuery query);
	
}
