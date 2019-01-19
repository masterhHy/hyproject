package com.hao.user.controller;

import com.hao.common.controller.BaseSpringController;
import com.hao.common.entity.user.SysAuthority;
import com.hao.common.entity.user.SysRole;
import com.hao.common.entity.user.SysUser;
import com.hao.common.pojo.ResponseData;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.SysAuthorityQuery;
import com.hao.remote.api.userservice.UserServiceRemote;
import com.hao.user.service.ResourceService;
import com.hao.user.service.RoleService;
import com.hao.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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
        userService.registUser(user);
        ResponseData<SysUser> res = new ResponseData<>();
        res.setData(user);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    public ResponseData<SysUser> getUserByRecord(@RequestBody SysUser user) {
        ResponseData<SysUser> res = new ResponseData<>();
        res.setData(userService.selectOne(user));
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    public ResponseData<SysUser> updateUserById(@RequestBody SysUser user) {

        ResponseData<SysUser> res = new ResponseData<>();
        userService.updateByPrimaryKeySelective(user);
        res.setData(user);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    @RequestMapping("/user/getMenu")
    public ResponseData<List<Map<String,Object>>> getAllMenu(){
        ResponseData<List<Map<String,Object>>> res = new ResponseData<>();
        List<Map<String,Object>> menus =  resourceService.getAllMenuByUserId(this.getUserId());
        res.setData(menus);
        return res;
    }
    
    @RequestMapping("/user/getALLAuth")
    public ResponseData<List<SysAuthority>> getALLAuth(){
        ResponseData<List<SysAuthority>> res = new ResponseData<>();
        List<SysAuthority> auth =  resourceService.getAllAuthority();
        res.setData(auth);
        return res;
    }
    @RequestMapping("/user/getSubAuth")
    public ResponseData<TableData<SysAuthority>> getSubAuth(SysAuthorityQuery query){
    	ResponseData<TableData<SysAuthority>> res = new ResponseData<>();
    	TableData<SysAuthority> table = resourceService.getSubAuthByParentId(query);
    	res.setData(table);
    	return res;
    }
    

    
    @RequestMapping("/user/addOrUpdateAuth")
    public ResponseData addOrUpdateAuth(SysAuthority authority){
        ResponseData res = new ResponseData<>();
        if(StringUtils.isNotBlank(authority.getIcon())){
            authority.setLastModifiedBy(this.getUser().getFirstName());
        }else{
            authority.setCreatedBy(this.getUser().getFirstName());
        }
        resourceService.addOrUpdateAuth(authority);
        return res;
    }



}
