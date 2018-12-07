package com.hao.remote.api.userservice;

import com.hao.common.entity.user.SysAuthority;
import com.hao.common.entity.user.SysRole;
import com.hao.common.entity.user.SysUser;
import com.hao.common.pojo.ResponseData;
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
    public static final String CONTEXT_PATH="/";

    @RequestMapping("/user/getAllAuthByUserId")
    public ResponseData<List<SysAuthority>> getAllAuthorityByUserId(@RequestParam("userId") String userId);
    @RequestMapping("/user/getUserByUsername")
    public ResponseData<SysUser> getUserByUsername(@RequestParam("userName") String userName);
    @RequestMapping("/user/getRoleByUserId")
    public ResponseData<List<SysRole>> getRoleByUserId(@RequestParam("userId") String userId);
}
