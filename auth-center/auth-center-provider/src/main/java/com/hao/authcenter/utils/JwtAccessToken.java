package com.hao.authcenter.utils;

import com.alibaba.fastjson.JSONObject;
import com.hao.authcenter.auth.BaseUserDetail;
import com.hao.common.entity.user.SysUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Map;

/**
 * 自定义JwtAccessToken转换器
 */
public class JwtAccessToken extends JwtAccessTokenConverter {

    /**
     * 生成token
     * @param accessToken
     * @param authentication
     * @return
     */
	private final String userInfo ="user_info";
	
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        DefaultOAuth2AccessToken defaultOAuth2AccessToken = new DefaultOAuth2AccessToken(accessToken);

        // 设置额外用户信息
       SysUser baseUser = ((BaseUserDetail) authentication.getPrincipal()).getBaseUser();
        baseUser.setPassword(null);
        // 将用户信息添加到token额外信息中

        defaultOAuth2AccessToken.getAdditionalInformation().put(userInfo,  JSONObject.toJSONString(baseUser));
        return super.enhance(defaultOAuth2AccessToken, authentication);
    }

    /**
     * 解析token 主要吧序列化的user对象转化成正常使用对象
     * @param value
     * @param map
     * @return
     */
    @Override
    public OAuth2AccessToken extractAccessToken(String value, Map<String, ?> map){
        OAuth2AccessToken oauth2AccessToken = super.extractAccessToken(value, map);
        convertData(oauth2AccessToken, oauth2AccessToken.getAdditionalInformation());
        return oauth2AccessToken;
    }

    private void convertData(OAuth2AccessToken accessToken,  Map<String, ?> map) {
        accessToken.getAdditionalInformation().put(userInfo,convertUserData(map.get(userInfo)));

    }

    private SysUser convertUserData(Object map) {
        SysUser user =JSONObject.parseObject(map.toString(),SysUser.class);
        return user;
    }

}
