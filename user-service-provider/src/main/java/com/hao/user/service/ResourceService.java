package com.hao.user.service;


import com.hao.common.entity.user.SysAuthority;

import java.util.List;
import java.util.Map;

public interface ResourceService extends BaseService<SysAuthority> {
    public List<SysAuthority> getAllAuthorityByUserId(String userId);
    /**
     * 获取该用户所有菜单权限
     * @param userId
     * @return
     */
    public List<Map<String,Object>> getAllMenuByUserId(String userId);
}
