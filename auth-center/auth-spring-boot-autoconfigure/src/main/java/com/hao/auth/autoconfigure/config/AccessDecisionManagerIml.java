package com.hao.auth.autoconfigure.config;

import com.hao.auth.autoconfigure.utils.AccessTokenUtils;
import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import java.util.*;
import java.util.stream.Collectors;


@ConfigurationProperties(prefix = "security")
public class AccessDecisionManagerIml  implements AccessDecisionManager {

    @Autowired
    private AccessTokenUtils accessTokenUtils;

    private AntPathMatcher matcher = new AntPathMatcher();

    private String[] ignoreds;

    @Value("${spring.application.name}")
    private String applicationName;

    private String url;



    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException,InsufficientAuthenticationException {
        // 请求路径
        url = getUrl(o);

        // 不拦截的请求
        for(String path : ignoreds){
            String temp = path.trim();
            if (matcher.match(temp, url)) {
                return;
            }
        }

        // URL 鉴权
        Iterator<SysAuthority> iterator = accessTokenUtils.getMenuInfo().iterator();
        while (iterator.hasNext()){
            SysAuthority auth = iterator.next();
            if(applicationName.equals(auth.getProjectName())&&this.matchUrl(url, auth.getUrl())){
            	return ;
            }
        }

        throw new AccessDeniedException("无权限！");

    }

    /**
     *  获取请求中的url
     */
    private String getUrl(Object o) {
        //获取当前访问url
        String url = ((FilterInvocation)o).getRequestUrl();
        int firstQuestionMarkIndex = url.indexOf("?");
        if (firstQuestionMarkIndex != -1) {
            return url.substring(0, firstQuestionMarkIndex);
        }
        return url;
    }



    private boolean matchUrl(String url, String modulePath) {

        List urls = Arrays.asList(url.split("/")).stream().filter(e -> !"".equals(e)).collect(Collectors.toList());
        Collections.reverse(urls);

        List paths = Arrays.asList(modulePath.split("/")).stream().filter(e -> !"".equals(e)).collect(Collectors.toList());
        Collections.reverse(paths);

        // 如果数量不相等
        if (urls.size() != paths.size()) {
            return false;
        }

        for(int i = 0; i < paths.size(); i++){
            // 如果是 PathVariable 则忽略
            String item = (String) paths.get(i);
            if (item.charAt(0) != '{' && item.charAt(item.length() - 1) != '}') {
                // 如果有不等于的，则代表 URL 不匹配
                if (!item.equals(urls.get(i))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    public void setIgnored(String ignored) {
    	System.out.println("=================="+ignored);
        if(ignored != null && !"".equals(ignored))
            this.ignoreds = ignored.split(",");
        else
            this.ignoreds = new String[]{};
    }
}
