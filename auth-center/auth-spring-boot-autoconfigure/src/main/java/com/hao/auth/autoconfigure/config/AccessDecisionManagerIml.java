package com.hao.auth.autoconfigure.config;

import com.hao.auth.autoconfigure.utils.AccessTokenUtils;
import com.hao.common.entity.user.SysAuthority;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.FilterInvocation;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * 自定义权限控制，给资源服务器用的
 */
@ConfigurationProperties(prefix = "security")
public class AccessDecisionManagerIml  implements AccessDecisionManager {

    @Autowired
    private AccessTokenUtils accessTokenUtils;

    private AntPathMatcher matcher = new AntPathMatcher();

    private List<String> ignoreds=new ArrayList<>();





    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException,InsufficientAuthenticationException {
        //如果是超级管理员，不作限制
        String id = accessTokenUtils.getUserInfo().getId();
        if(id.equals("1")){
            HttpServletRequest request = ((FilterInvocation)o).getRequest();
            request.setAttribute("user_info", accessTokenUtils.getUserInfo());
            return ;
        }

        // 请求路径
        String url = getUrl(o);

        // 不拦截的请求
        for(String path : ignoreds){
            String temp = path.trim();
            if (matcher.match(temp, url)) {
                return;
            }
        }

        // URL 鉴权 先找出该url对应的项目 在进行鉴权
        List<String> projectNameList=  accessTokenUtils.getAllProjectName();
        String projectName="";
        for (String name : projectNameList){
            if(url.indexOf(name)!=-1){
                projectName=name;
                url = url.replace("/"+name+"/api","");
            }
        }
        if(StringUtils.isBlank(projectName)){
            throw new AccessDeniedException("无权限");
        }


        Iterator<SysAuthority> iterator = accessTokenUtils.getMenuInfo(projectName).iterator();
        while (iterator.hasNext()){
            SysAuthority auth = iterator.next();

            if(this.matchUrl(url, auth.getUrl())){
            	HttpServletRequest request = ((FilterInvocation)o).getRequest();
            	request.setAttribute("user_info", accessTokenUtils.getUserInfo());
                return ;
            }
        }

        throw new AccessDeniedException("无权限");

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

    public void setAppIgnore(List<Map<String, String>> appIgnore) {
    	ignoreds.add("/actuator/health");
    	for (Map<String, String> map : appIgnore) {
			for (String key : map.keySet()) {
				String urls = map.get(key);
				if(StringUtils.isNotBlank(urls)){

					String[] urlarr = urls.split(",");
					for (String url : urlarr) {
						ignoreds.add("/"+key+"/api"+url);// 如/user/** user应用的所有接口暴露
					}
				}
				
			}
		}
    	
    }
}
