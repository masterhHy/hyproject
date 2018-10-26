package com.hao.auth.autoconfigure.utils;

import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


/**
 * Created by fp295 on 2018/4/29.
 */
public class AccessTokenUtils {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenExtractor tokenExtractor;


    @Autowired
    private RedisTemplate<String, SysAuthority> baseModelTemplate;

    /**
     * 生成token
     * @param accessToken
     * @param authentication
     * @return
     */
    private final String userInfo ="user_info";


    /**
     * 从token获取用户信息
     * @return
     */
    public SysUser getUserInfo() throws AccessDeniedException {
        return (SysUser) this.getAccessToken().getAdditionalInformation().get(userInfo);
    }


    public List<SysAuthority> getMenuInfo()throws AccessDeniedException{
        String key = getUserInfo().getId() + "-menu";
        long size = baseModelTemplate.opsForList().size(key);
        return baseModelTemplate.opsForList().range(key, 0, size);
    }

    private OAuth2AccessToken getAccessToken() throws AccessDeniedException {
        OAuth2AccessToken token;
        // 抽取token
        Authentication a = tokenExtractor.extract(request);
        try {
            // 调用JwtAccessTokenConverter的extractAccessToken方法解析token
            token = tokenStore.readAccessToken((String) a.getPrincipal());
        } catch(Exception e) {
            throw new AccessDeniedException("AccessToken Not Found.");
        }
        return token;
    }
}
