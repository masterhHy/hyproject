package com.hao.authcenter.remote;

import com.hao.user.remote.UserServiceRemote;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name=UserServiceRemote.FeignClientName,path = UserServiceRemote.CONTEXT_PATH)
public interface UserServiceClient extends UserServiceRemote {
	
    

}

