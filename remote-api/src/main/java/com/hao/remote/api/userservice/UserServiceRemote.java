package com.hao.remote.api.userservice;

import com.hao.common.entity.user.SysAuthority;
import com.hao.common.entity.user.SysRole;
import com.hao.common.entity.user.SysUser;
import com.hao.common.pojo.ResponseData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 对外开放的接口
 * @author Administrator
 *
 */
public interface UserServiceRemote {
	public static final String FeignClientName="user-service-provider";
    public static final String CONTEXT_PATH="/";

    @RequestMapping("/user/getAllAuthByUserId")
    public ResponseData<List<SysAuthority>> getAllAuthorityByUserId(@RequestParam("userId") String userId);
    @RequestMapping("/user/getUserByUsername")
    public ResponseData<SysUser> getUserByUsername(@RequestParam("userName") String userName);
    @RequestMapping("/user/getRoleByUserId")
    public ResponseData<List<SysRole>> getRoleByUserId(@RequestParam("userId") String userId);
    @RequestMapping(value = "/user/register",method = RequestMethod.POST)
    public ResponseData<SysUser> register(@RequestBody SysUser user);
    @RequestMapping(value = "/user/getUserByRecord",method = RequestMethod.POST)
    public ResponseData<SysUser> getUserByRecord(@RequestBody SysUser user);
    @RequestMapping(value = "/user/updateUserById",method = RequestMethod.POST)
    public ResponseData<SysUser> updateUserById(@RequestBody SysUser user);
}
