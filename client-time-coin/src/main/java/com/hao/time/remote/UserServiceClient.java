package com.hao.time.remote;

import org.springframework.cloud.openfeign.FeignClient;

import com.hao.remote.api.userservice.UserServiceRemote;

@FeignClient(name=UserServiceRemote.FeignClientName,path = UserServiceRemote.CONTEXT_PATH)
public interface UserServiceClient extends UserServiceRemote {

}
