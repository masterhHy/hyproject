package com.hao.user.service;


import com.hao.common.entity.user.SysUser;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysUserQuery;

public interface UserService extends BaseService<SysUser> {
	
	public void initSysUser();

	public void registUser(SysUser user);
	
	public void refreshRedisUser();
	
	public TableData<SysUser> getUserData(SysUserQuery query);
	
}
