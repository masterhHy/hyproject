package com.hao.user.dao;

import com.hao.common.entity.user.SysAuthority;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface SysAuthorityMapper extends Mapper<SysAuthority> {

    public List<SysAuthority> getAllAuthority(@Param("userId") String userId,@Param("type") Integer type);

    public Map<String,Object> getMaxCode();
}