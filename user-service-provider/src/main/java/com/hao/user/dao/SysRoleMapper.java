package com.hao.user.dao;

import com.hao.common.entity.user.SysRole;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;
@org.apache.ibatis.annotations.Mapper
public interface SysRoleMapper extends Mapper<SysRole> {
	
	public List<SysRole> getRoleByUserId(@Param("userId")String userId);
	public Map<String,Object> getMaxCode();
	
	public List<SysRole> getUserRoleData(@Param("userId")String userId,@Param("hisRole")Boolean hisRole,@Param("name")String name);
}