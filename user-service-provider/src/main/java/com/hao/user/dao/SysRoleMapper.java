package com.hao.user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.hao.user.entity.SysRole;
import tk.mybatis.mapper.common.Mapper;
@org.apache.ibatis.annotations.Mapper
public interface SysRoleMapper extends Mapper<SysRole> {
	
	public List<SysRole> getRoleByUserId(@Param("userId")String userId);
}