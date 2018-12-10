package com.hao.auth.autoconfigure.utils;

import com.hao.common.constant.DataBaseConstant;
import com.hao.common.entity.user.SysAuthority;
import com.hao.common.entity.user.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.security.oauth2.provider.token.TokenStore;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AccessTokenUtils {

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private TokenExtractor tokenExtractor;


    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

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
        SysUser user = (SysUser) this.getAccessToken().getAdditionalInformation().get(userInfo);
        SysUser redisUser = (SysUser) redisTemplate.opsForValue().get(DataBaseConstant.REDIS_USER_NAME_PLACE+user.getId()+"-user");
        if(redisUser==null){
            throw new InsufficientAuthenticationException("没登录访问保护资源");
        }
        return user;
    }

    public List<String> getAllProjectName(){
        List<String> res = new ArrayList<>();
        String key = DataBaseConstant.REDIS_USER_NAME_PLACE+getUserInfo().getId() + "-menu";
        Map<Object,Object> map =redisTemplate.opsForHash().entries(key);

        for (Object objKey: map.keySet()){
            res.add(objKey.toString());
        }
        return res;
    }


    public List<SysAuthority> getMenuInfo(String projectName)throws AccessDeniedException{
        String key = DataBaseConstant.REDIS_USER_NAME_PLACE+getUserInfo().getId() + "-menu";
        List<SysAuthority>  res= (List<SysAuthority>) redisTemplate.opsForHash().get(key,projectName);
        return res;
    }

    private OAuth2AccessToken getAccessToken() throws AccessDeniedException {
        OAuth2AccessToken token;
        // 抽取token] 把request header 中key Authorization value ‘Bearer f732723d-af7f-41bb-bd06-2636ab2be135’这种格式抽取出来
        //f732723d-af7f-41bb-bd06-2636ab2be135 即为token
        Authentication a = tokenExtractor.extract(request);
        try {
            // 调用JwtAccessTokenConverter的extractAccessToken方法解析token
            token = tokenStore.readAccessToken((String) a.getPrincipal());
        } catch(Exception e) {
            throw new InsufficientAuthenticationException("没登录访问保护资源");
        }
        return token;
    }
}
