package com.hao.authcenter.auth.config;


import com.hao.authcenter.auth.BaseUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 * 
 * @author Administrator
 * 应用对外暴露接口的安全配置类
 */
@Configuration
@Order(10)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    // 自动注入UserDetailsService
    @Autowired
    private BaseUserDetailService baseUserDetailService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String [] arr = new String []{
                "/css/**","/js/**",
                "/favicon.ico","/webjars/**",
                "/images/**",
                "/refresh","/oauth/deleteToken","/oauth/token_key",
                "/backReferer","/actuator/**"
        };
       http
                .formLogin().loginPage("/login").permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/backReferer")
                .and().authorizeRequests().antMatchers(arr).permitAll()
                .and().authorizeRequests().anyRequest().authenticated();
    }

    /**
     * 用户验证
     * @param auth
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(baseUserDetailService).passwordEncoder(new BCryptPasswordEncoder(6));
        auth.parentAuthenticationManager(authenticationManagerBean());
    }



    

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
