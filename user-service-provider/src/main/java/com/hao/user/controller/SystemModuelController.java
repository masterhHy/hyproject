package com.hao.user.controller;

import com.hao.common.controller.BaseSpringController;
import com.hao.common.entity.user.OauthClientDetails;
import com.hao.common.entity.user.SysAuthority;
import com.hao.common.entity.user.SysRole;
import com.hao.common.entity.user.SysUser;
import com.hao.common.pojo.ResponseData;
import com.hao.common.pojo.TableData;
import com.hao.common.query.user.OauthClientDetailsQuery;
import com.hao.common.query.user.SysAuthorityQuery;
import com.hao.common.query.user.SysRoleQuery;
import com.hao.common.query.user.SysUserQuery;
import com.hao.user.service.ClientService;
import com.hao.user.service.ResourceService;
import com.hao.user.service.RoleService;
import com.hao.user.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class SystemModuelController extends BaseSpringController  {

    @Autowired
    private ResourceService resourceService;
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private ClientService clientService;
/**************************************权限模块接口*******************************************************************/
    @RequestMapping("/user/getMenu")
    public ResponseData<List<Map<String,Object>>> getAllMenu(){
        ResponseData<List<Map<String,Object>>> res = new ResponseData<>();
        List<Map<String,Object>> menus =  resourceService.getAllMenuByUserId(this.getUserId());
        res.setData(menus);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    @RequestMapping("/user/getALLAuth")
    public ResponseData<List<SysAuthority>> getALLAuth(){
        ResponseData<List<SysAuthority>> res = new ResponseData<>();
        List<SysAuthority> auth =  resourceService.getAllAuthority();
        res.setData(auth);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }
    @RequestMapping("/user/getSubAuth")
    public ResponseData<TableData<SysAuthority>> getSubAuth(SysAuthorityQuery query){
        ResponseData<TableData<SysAuthority>> res = new ResponseData<>();
        TableData<SysAuthority> table = resourceService.getSubAuthByParentId(query);
        res.setData(table);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }



    @RequestMapping("/user/addOrUpdateAuth")
    public ResponseData addOrUpdateAuth(SysAuthority authority){
        ResponseData res = new ResponseData<>();
        resourceService.addOrUpdateAuth(authority);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }
    @RequestMapping("/user/deleteAuthById")
    public ResponseData deleteAuthById(SysAuthority authority){
        ResponseData res = new ResponseData<>();
        if(StringUtils.isNotBlank(authority.getId())){
            resourceService.deleteAuthById(authority.getId());
        }
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }
/***********************************************************************************************************************/

/***********************************应用模块接口***************************************************************/

    @RequestMapping("/client/getClientData")
    public ResponseData<TableData<OauthClientDetails>> getClientData(OauthClientDetailsQuery query){
        ResponseData<TableData<OauthClientDetails>> res = new ResponseData<>();
        TableData<OauthClientDetails> tableData =clientService.getClientData(query);
        res.setData(tableData);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }
    @RequestMapping("/client/deleteClientById")
    public ResponseData deleteClientById(OauthClientDetails client){
        ResponseData res = new ResponseData<>();
        if(StringUtils.isNotBlank(client.getClientId())){
            clientService.deleteByPrimaryKey(client.getClientId());
        }
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }

    @RequestMapping("/client/addOrUpdateClient")
    public ResponseData addOrUpdateClient(OauthClientDetails client){
        ResponseData res = new ResponseData<>();
        clientService.addOrUpdateClient(client);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }
    @RequestMapping("/client/checkClientId")
    public ResponseData checkClientId(OauthClientDetails client){
        ResponseData res = new ResponseData<>();
        OauthClientDetails details =clientService.selectByPrimaryKey(client);
        if(details==null){
            res.setStatus(true);
        }else{
            res.setStatus(false);
            res.setMessage("已经存在clientId");
        }
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }


/***********************************************************************************************************************/

/****************************************角色模块*****************************************************/
    @RequestMapping("/role/getRoleData")
    public ResponseData<TableData<Map<String,Object>>> getRoleData(SysRoleQuery query){
        ResponseData<TableData<Map<String,Object>>> res = new ResponseData<>();
        TableData<Map<String,Object>> tableData =roleService.getRoleData(query);
        res.setData(tableData);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;

    }
    @RequestMapping("/role/deleteRoleById")
    public ResponseData deleteRoleById(SysRoleQuery query){
    	ResponseData res = new ResponseData();
    	if(StringUtils.isNotBlank(query.getId())){
    		roleService.deleteRoleById(query.getId());
        }
    	res.setCode(ResponseData.SUCCESS_CODE);
    	return res;
    }
    
    @RequestMapping("/role/addOrUpdateRole")
    public ResponseData addOrUpdateRole(SysRole role,String auth){
        ResponseData res = new ResponseData<>();
        roleService.addOrUpdateRole(role,auth);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;
    }
    @RequestMapping("/role/getRoleUserData")
    public ResponseData<TableData<SysUser>> getRoleUserData(SysRoleQuery query){
        ResponseData<TableData<SysUser>> res = new ResponseData<>();
        TableData<SysUser> tableData =roleService.getRoleUserData(query);
        res.setData(tableData);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;

    }
    @RequestMapping("/role/addUserToRole")
    public ResponseData addUserToRole(String userIds,String roleId){
    	ResponseData res = new ResponseData<>();
    	roleService.addUserToRole(roleId,userIds);
    	res.setCode(ResponseData.SUCCESS_CODE);
    	return res;
    	
    }
    @RequestMapping("/role/deleteUserFromRole")
    public ResponseData deleteUserToRole(String userIds,String roleId){
    	ResponseData res = new ResponseData<>();
    	roleService.deleteUserToRole(roleId,userIds);
    	res.setCode(ResponseData.SUCCESS_CODE);
    	return res;
    	
    }



/***********************************************************************************************************************/
    @RequestMapping("/user/getUserData")
    public ResponseData<TableData<SysUser>> getUserData(SysUserQuery query){
        ResponseData<TableData<SysUser>> res = new ResponseData<>();
        TableData<SysUser> tableData =userService.getUserData(query);
        res.setData(tableData);
        res.setCode(ResponseData.SUCCESS_CODE);
        return res;

    }
    
    
/****************************************角色模块*****************************************************/
   
    
    
/***********************************************************************************************************************/
   
    
}
