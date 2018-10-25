package com.hao.user.controller;

import com.hao.common.pojo.ResponseData;
import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysRole;
import com.hao.user.entity.SysUser;
import com.hao.user.remote.UserServiceRemote;
import com.hao.user.service.ResourceService;
import com.hao.user.service.RoleService;
import com.hao.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
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

    public ResponseData<List<SysAuthority>> getAllAuthorityByUserId(String userId){
        ResponseData<List<SysAuthority>> res = new ResponseData<>();
        List<SysAuthority> list =resourceService.getAllAuthorityByUserId(userId);
        res.setData(list);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }
    public ResponseData<SysUser> selectOneUser(SysUser user){
        ResponseData<SysUser>res = new ResponseData<>();
        res.setData(userService.selectOne(user));
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    public ResponseData<List<SysRole>> getRoleByUserId(String userId){
        ResponseData<List<SysRole>>res = new ResponseData<>();
        res.setData(roleService.getRoleByUserId(userId));
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }



}
