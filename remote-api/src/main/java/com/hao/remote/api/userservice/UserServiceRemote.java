package com.hao.remote.api.userservice;

import com.hao.common.pojo.ResponseData;
import com.hao.remote.api.userservice.entity.RemoteSysAuthority;
import com.hao.remote.api.userservice.entity.RemoteSysRole;
import com.hao.remote.api.userservice.entity.RemoteSysUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * 对外开放的接口
 * @author Administrator
 *
 */
public interface UserServiceRemote {
	public static final String FeignClientName="user-service-provider";
    public static final String CONTEXT_PATH="/user";

    @RequestMapping("/user/getAllAuthByUserId")
    public ResponseData<List<RemoteSysAuthority>> getAllAuthorityByUserId(@RequestParam("userId") String userId);
    @RequestMapping("/user/getUserByUsername")
    public ResponseData<RemoteSysUser> getUserByUsername(@RequestParam("userName") String userName);
    @RequestMapping("/user/getRoleByUserId")
    public ResponseData<List<RemoteSysRole>> getRoleByUserId(@RequestParam("userId") String userId);
}
