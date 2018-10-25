package com.hao.authcenter.remote;

import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysRole;
import com.hao.user.entity.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@FeignClient
public interface UserServiceRemoteClient   {
    @RequestMapping("/user/getAllAuthByUserId")
    public List<SysAuthority> getAllAuthorityByUserId(String userId);

    @RequestMapping("/user/getUser")
    public SysUser selectOneUser(SysUser user);

    @RequestMapping("/user/getRoleByUserId")
    public List<SysRole> getRoleByUserId(String userId);

}
