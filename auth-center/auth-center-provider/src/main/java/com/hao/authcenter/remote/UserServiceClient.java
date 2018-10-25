package com.hao.authcenter.remote;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import com.hao.authcenter.remote.UserServiceClient.UserServiceFallBack;
import com.hao.common.pojo.ResponseData;
import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysRole;
import com.hao.user.entity.SysUser;
import com.hao.user.remote.UserServiceRemote;

@FeignClient(name=UserServiceRemote.FeignClientName,fallback=UserServiceFallBack.class)
public interface UserServiceClient extends UserServiceRemote {
	
    
    public class UserServiceFallBack implements UserServiceClient{

		@Override
		public ResponseData<List<SysAuthority>> getAllAuthorityByUserId(String userId) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResponseData<SysUser> selectOneUser(SysUser user) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public ResponseData<List<SysRole>> getRoleByUserId(String userId) {
			// TODO Auto-generated method stub
			return null;
		}

		
    	
    }
}
