package com.hao.user.service;

import com.hao.user.entity.SysAuthority;

import java.util.List;
public interface ResourceService extends BaseService<SysAuthority> {
    public List<SysAuthority> getAllAuthorityByUserId(String userId);
}
