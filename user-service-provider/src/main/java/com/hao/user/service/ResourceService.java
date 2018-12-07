package com.hao.user.service;


import com.hao.common.entity.user.SysAuthority;

import java.util.List;
public interface ResourceService extends BaseService<SysAuthority> {
    public List<SysAuthority> getAllAuthorityByUserId(String userId);
}
