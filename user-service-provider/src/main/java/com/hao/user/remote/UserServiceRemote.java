package com.hao.user.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hao.common.pojo.ResponseData;
import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysRole;
import com.hao.user.entity.SysUser;


/**
 * 对外开放的接口
 * @author Administrator
 *
 */
public interface UserServiceRemote   {
	public static final String FeignClientName="user-service-provider";
    @RequestMapping("/user/getAllAuthByUserId")
    public ResponseData<List<SysAuthority>> getAllAuthorityByUserId(String userId);
    @RequestMapping("/user/getUser")
    public ResponseData<SysUser> selectOneUser(SysUser user);
    @RequestMapping("/user/getRoleByUserId")
    public ResponseData<List<SysRole>> getRoleByUserId(String userId);
}
