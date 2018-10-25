package com.hao.user.service.impl;

import com.hao.user.dao.SysAuthorityMapper;
import com.hao.user.entity.SysAuthority;
import com.hao.user.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl extends BaseServiceImpl<SysAuthority> implements ResourceService {
    @Autowired
    private SysAuthorityMapper mapper;

    @Override
    public List<SysAuthority> getAllAuthorityByUserId(String userId) {
        return mapper.getAllAuthorityByUserId(userId);
    }
}
