package com.hao.user.controller;

import com.hao.common.pojo.ResponseData;
import com.hao.remote.api.userservice.UserServiceRemote;
import com.hao.remote.api.userservice.entity.RemoteSysAuthority;
import com.hao.remote.api.userservice.entity.RemoteSysRole;
import com.hao.remote.api.userservice.entity.RemoteSysUser;
import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysUser;
import com.hao.user.service.ResourceService;
import com.hao.user.service.RoleService;
import com.hao.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController implements UserServiceRemote {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public ResponseData<List<RemoteSysAuthority>> getAllAuthorityByUserId(String userId){
        ResponseData<List<RemoteSysAuthority>> res = new ResponseData<>();
        List<SysAuthority> list =resourceService.getAllAuthorityByUserId(userId);
        res.setData(res.convertData(list,RemoteSysAuthority.class));
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }
    public ResponseData<RemoteSysUser> getUserByUsername(String userName){
        ResponseData<RemoteSysUser>res = new ResponseData<>();
        SysUser record = new SysUser();
        record.setUsername(userName);
        res.setData(userService.selectOne(record));
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    public ResponseData<List<RemoteSysRole>> getRoleByUserId(String userId){
        ResponseData<List<RemoteSysRole>>res = new ResponseData<>();
        List<RemoteSysRole> list =res.convertData(roleService.getRoleByUserId(userId),RemoteSysRole.class);
        res.setData(list);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }



}
