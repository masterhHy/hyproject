package com.hao.auth.autoconfigure.config;

import com.hao.auth.autoconfigure.exception.AuthenticationEntryPointImpl;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.authentication.BearerTokenExtractor;
import org.springframework.security.oauth2.provider.authentication.TokenExtractor;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http    // 配置授权管理器
                .authorizeRequests().accessDecisionManager(getAccessDecisionManager())
                // 匹配全部请求鉴权认证
                .anyRequest().authenticated()
                //权限不足处理器
                .and().exceptionHandling().authenticationEntryPoint(getAuthenticationEntryPointImpl())
                // 由于使用的是JWT，我们这里不需要csrf
                .and().csrf().disable();
    }


    @Bean
    public AccessDecisionManager getAccessDecisionManager() {
        return new AccessDecisionManagerIml();
    }

    @Bean
    public TokenExtractor getTokenExtractor() {
        return new BearerTokenExtractor();
    }
    @Bean
    public AuthenticationEntryPointImpl getAuthenticationEntryPointImpl(){
        return  new AuthenticationEntryPointImpl();
    }
    /**
     * 跨域
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean =  new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

}
