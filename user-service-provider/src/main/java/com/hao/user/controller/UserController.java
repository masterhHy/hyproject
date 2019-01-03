package com.hao.user.controller;

import com.hao.common.controller.BaseSpringController;
import com.hao.common.entity.user.SysAuthority;
import com.hao.common.entity.user.SysRole;
import com.hao.common.entity.user.SysUser;
import com.hao.common.pojo.ResponseData;
import com.hao.remote.api.userservice.UserServiceRemote;
import com.hao.user.service.ResourceService;
import com.hao.user.service.RoleService;
import com.hao.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController extends BaseSpringController implements UserServiceRemote {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    public ResponseData<List<SysAuthority>> getAllAuthorityByUserId(String userId){
        ResponseData<List<SysAuthority>> res = new ResponseData<>();
        List<SysAuthority> list =resourceService.getAllAuthorityByUserId(userId);
        res.setData(list);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }
    public ResponseData<SysUser> getUserByUsername(String userName){
        ResponseData<SysUser>res = new ResponseData<>();
        SysUser record = new SysUser();
        record.setUsername(userName);
        res.setData(userService.selectOne(record));
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    public ResponseData<List<SysRole>> getRoleByUserId(String userId){
        ResponseData<List<SysRole>>res = new ResponseData<>();
        List<SysRole> list =roleService.getRoleByUserId(userId);
        res.setData(list);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    public ResponseData<SysUser> register(@RequestBody SysUser user) {
        System.out.println(user);
        userService.registUser(user);
        ResponseData<SysUser> res = new ResponseData<>();
        res.setData(user);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }


}
