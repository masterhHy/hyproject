package com.hao.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hao.common.entity.user.SysUser;

import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface SysUserMapper extends Mapper<SysUser> {
	
	public List<SysUser> getRoleUserData(@Param("roleId")String roleId,@Param("hisUser")Boolean hisUser,@Param("username")String username);
}