package com.hao.authcenter.auth.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hao.authcenter.auth.UsernameLoginDetailService;
import com.hao.authcenter.auth.ohterlogin.phone.PhoneAuthenticationProvider;
import com.hao.authcenter.auth.ohterlogin.phone.PhoneLoginAuthenticationFilter;
import com.hao.authcenter.auth.ohterlogin.phone.PhoneLoginDetailService;
import com.hao.authcenter.auth.ohterlogin.wx.WxAuthenticationProvider;
import com.hao.authcenter.auth.ohterlogin.wx.WxLoginAuthenticationFilter;
import com.hao.authcenter.auth.ohterlogin.wx.WxLoginDetailService;


/**
 * 
 * @author Administrator
 * 应用对外暴露接口的安全配置类
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsernameLoginDetailService usernameLoginDetailService;
    @Autowired
    private PhoneLoginDetailService phoneLoginDetailService;
    @Autowired
    private WxLoginDetailService wxLoginDetailService;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String [] arr = new String []{
                "/css/**","/js/**","/plugins/**","/images/**","/img/**",
                "/favicon.ico","/webjars/**",
                "/refresh","/oauth/deleteToken","/oauth/token_key","/open/**",
                "/backReferer","/actuator/**"
        };
       http
       			.addFilterBefore(getPhoneLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
       			.addFilterBefore(getWxLoginAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
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
        //基本账号密码登录
    	auth.userDetailsService(usernameLoginDetailService).passwordEncoder(new BCryptPasswordEncoder(6));
        //自定义登陆  注册
    	
    	//电话号码 登陆
    	auth.authenticationProvider(phoneAuthenticationProvider());
    	//wx code 登陆
    	auth.authenticationProvider(wxAuthenticationProvider());
    }
    
    /**********************手机验证码登陆需要的类****************************************/
    @Bean
    public PhoneAuthenticationProvider phoneAuthenticationProvider(){
        PhoneAuthenticationProvider provider = new PhoneAuthenticationProvider();
        provider.setUserDetailsService(phoneLoginDetailService);
        return provider;
    }
    /**
     * 手机验证码登陆过滤器
     * @return
     */
    @Bean
    public PhoneLoginAuthenticationFilter getPhoneLoginAuthenticationFilter() {
        PhoneLoginAuthenticationFilter filter = new PhoneLoginAuthenticationFilter();
        try {
            filter.setAuthenticationManager(this.authenticationManagerBean());
        } catch (Exception e) {
            e.printStackTrace();
        }
        filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
        return filter;
    }
    /**************************************************************/
    
    /**********************微信code登陆****************************************/
    @Bean
    public WxAuthenticationProvider wxAuthenticationProvider(){
    	WxAuthenticationProvider provider = new WxAuthenticationProvider();
    	provider.setUserDetailsService(wxLoginDetailService);
    	return provider;
    }
    @Bean
    public WxLoginAuthenticationFilter getWxLoginAuthenticationFilter() {
    	WxLoginAuthenticationFilter filter = new WxLoginAuthenticationFilter();
    	try {
    		filter.setAuthenticationManager(this.authenticationManagerBean());
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	filter.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler("/login?error"));
    	return filter;
    }
    /**************************************************************/

    

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
