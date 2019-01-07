package com.hao.authcenter.auth.ohterlogin.wx;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.hao.authcenter.auth.BaseUserDetailService;
import com.hao.authcenter.remote.UserServiceClient;
import com.hao.common.constant.WxConstant;
import com.hao.common.entity.user.SysUser;
import com.hao.common.utils.HTTPUtils;
import com.hao.common.utils.UUID;


@Service
public class WxLoginDetailService extends BaseUserDetailService {
	
	@Autowired
    private UserServiceClient userServiceClient;
	
	protected SysUser loadUser(String code){
		SysUser user = null;
		try {
			if(StringUtils.isNotBlank(code)){
				//利用code值获取用户信息
				String tokenUrl="https://api.weixin.qq.com/sns/oauth2/access_token";
				String param ="appid=#1&secret=#2&code=#3&grant_type=authorization_code";
				param = param.replace("#1",WxConstant.APP_ID)
						.replace("#2",WxConstant.APP_SECRET)
						.replace("#3",code);
				String backMsg = HTTPUtils.sendGet(tokenUrl,param);
				JSONObject token = JSONObject.parseObject(backMsg);
				String accessToken = token.get("access_token").toString();
				String openid =token.get("openid").toString();
				SysUser record = new SysUser();
				record.setId(openid);
				user = userServiceClient.getUserByRecord(record).getData();
				
				//获取用户信息
				String userInfoUrl="https://api.weixin.qq.com/sns/userinfo";
				String userParam ="access_token=#1&openid=#2";
				userParam = userParam.replace("#1",accessToken).replace("#2",openid);
				String userMsg = HTTPUtils.sendGet(userInfoUrl,userParam);
				JSONObject userInfo = JSONObject.parseObject(userMsg);
				String nickname = userInfo.get("nickname").toString();
				String headimgurl =  userInfo.get("headimgurl").toString();
				String sex =  userInfo.get("sex").toString();
				if(user==null){
					//用户第一次微信登录 没有在系统里
					
					//注册成为本系统用户
					user = new SysUser();
					user.setId(openid);
					user.setRegisterSource(2);
					user.setFirstName(nickname);
					user.setHeadImg(headimgurl);
					user.setSex("1".equals(sex)?0:1);
					userServiceClient.register(user);
				}else{
					user.setFirstName(nickname);
					user.setHeadImg(headimgurl);
					user.setSex("1".equals(sex)?0:1);
					user.setLastModifiedDate(new Date());
					userServiceClient.updateUserById(user);
				}
			}
			
		} catch (Exception e) {
			logger.error("",e);
			throw new UsernameNotFoundException("授权失败!请重新授权");
		}
		if(user!=null){
			user.setUsername(user.getFirstName());
			user.setPassword("temp-"+UUID.uuid32());
		}
		
		
		// 调用FeignClient查询用户
		return user;
	}

}
