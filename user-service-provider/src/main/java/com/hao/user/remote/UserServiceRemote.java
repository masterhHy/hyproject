package com.hao.user.remote;

import com.hao.common.pojo.ResponseData;
import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysRole;
import com.hao.user.entity.SysUser;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 对外开放的接口
 * @author Administrator
 *
 */
public interface UserServiceRemote   {
	public static final String FeignClientName="user-service-provider";
    public static final String CONTEXT_PATH="/user";

    @RequestMapping("/user/getAllAuthByUserId")
    public ResponseData<List<SysAuthority>> getAllAuthorityByUserId(@RequestParam("userId")String userId);
    @RequestMapping("/user/getUser")
    public ResponseData<SysUser> selectOneUser(@RequestBody SysUser user);
    @RequestMapping("/user/getRoleByUserId")
    public ResponseData<List<SysRole>> getRoleByUserId(@RequestParam("userId")String userId);
}
