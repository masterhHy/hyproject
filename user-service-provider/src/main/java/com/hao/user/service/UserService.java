package com.hao.user.service;


import com.hao.common.entity.user.SysUser;

public interface UserService extends BaseService<SysUser> {
	
	public void initSysUser();

	public void registUser(SysUser user);
}
