package com.hao.user.dao;

import com.hao.user.entity.SysAuthority;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
public interface SysAuthorityMapper extends Mapper<SysAuthority> {

    public List<SysAuthority> getAllAuthorityByUserId(@Param("userId") String userId);
}