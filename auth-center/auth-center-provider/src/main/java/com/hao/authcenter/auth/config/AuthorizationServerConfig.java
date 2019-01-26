package com.hao.authcenter.auth.config;


import com.alibaba.fastjson.JSONObject;
import com.hao.authcenter.auth.UsernameLoginDetailService;
import com.hao.authcenter.remote.UserServiceClient;
import com.hao.authcenter.utils.JwtAccessToken;
import com.hao.common.constant.DataBaseConstant;
import com.hao.common.entity.user.SysAuthority;
import com.hao.common.entity.user.SysUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.client.JdbcClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import org.springframework.security.rsa.crypto.KeyStoreKeyFactory;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author Administrator
 * 安全框架自定义权限配置
 */
@Configuration
@Order(2)
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private UsernameLoginDetailService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    private Logger logger = LoggerFactory.getLogger(AuthorizationServerConfig.class);




    @Bean("jdbcClientDetailsService")
    public JdbcClientDetailsService getJdbcClientDetailsService() {
        JdbcClientDetailsService clientDetailsService = new JdbcClientDetailsService(dataSource);
        return clientDetailsService;
    }


    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        JdbcClientDetailsService clientDetailsService= getJdbcClientDetailsService();
        try{
            clientDetailsService.loadClientByClientId("admin");
        }catch (NoSuchClientException e){
            clients.jdbc(dataSource).withClient("admin")
                    .authorizedGrantTypes("authorization_code","client_credentials","password", "refresh_token","implicit")
                    .scopes("read", "write").authorities("ALL").accessTokenValiditySeconds(60*60*24*100).refreshTokenValiditySeconds(60*24*100)
                    .secret("{noop}123456").autoApprove(false).and().build();
            logger.info("系统创建admin client");
        }

    	// 使用JdbcClientDetailsService客户端详情服务
        clients.withClientDetails(clientDetailsService);
    }

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public TokenStore redisTokenStore(){
        return new MyTokenStore(redisConnectionFactory);
    }

    class MyTokenStore extends RedisTokenStore {
        @Autowired
        private RedisTemplate<String, Object> redisTemplate;
        @Autowired
        private UserServiceClient userService;


        public MyTokenStore(RedisConnectionFactory connectionFactory) {
            super(connectionFactory);
        }
        public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {

            String userStr =  token.getAdditionalInformation().get("user_info").toString();
            SysUser pageUser = JSONObject.parseObject(userStr,SysUser.class);
            SysUser record = new SysUser();
            record.setId(pageUser.getId());
            //跟新redis user对象
            SysUser sysUser = userService.getUserByRecord(record).getData();
            redisTemplate.opsForValue().set(DataBaseConstant.REDIS_USER_NAME_PLACE+sysUser.getId()+"-user",sysUser);

            //更新redis user对应的auth
            redisTemplate.delete(DataBaseConstant.REDIS_USER_NAME_PLACE+sysUser.getId()+"-menu");
            List<SysAuthority> authList = userService.getAllAuthorityByUserId(sysUser.getId()).getData();
            authList.forEach(e -> {
                if (e.getUrl() != null) {
                    //存储权限到redis 以map形式存放 key为projectName value 为list
                    if (redisTemplate.opsForHash().hasKey(DataBaseConstant.REDIS_USER_NAME_PLACE + sysUser.getId() + "-menu", e.getProjectName())) {
                        //如果redis有list 就拿出来 吧权限放到list 然后在把list放回redis
                        List<SysAuthority> list = (List<SysAuthority>) redisTemplate.opsForHash().get(DataBaseConstant.REDIS_USER_NAME_PLACE + sysUser.getId() + "-menu", e.getProjectName());
                        list.add(e);
                        redisTemplate.opsForHash().put(DataBaseConstant.REDIS_USER_NAME_PLACE + sysUser.getId() + "-menu", e.getProjectName(), list);
                    } else {
                        //如果没有list，创建一个 然后放回去
                        List<SysAuthority> list = new ArrayList<>();
                        list.add(e);
                        redisTemplate.opsForHash().put(DataBaseConstant.REDIS_USER_NAME_PLACE + sysUser.getId() + "-menu", e.getProjectName(), list);
                    }
                }
            });

            super.storeAccessToken(token,authentication);
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.authenticationManager(authenticationManager)
                // 配置JwtAccessToken转换器
                .accessTokenConverter(jwtAccessTokenConverter())
                .tokenStore(redisTokenStore())
                // refresh_token需要userDetailsService
                .reuseRefreshTokens(false).userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer
                // 开启/oauth/token_key验证端口无权限访问
                .tokenKeyAccess("permitAll()")
                // 开启/oauth/check_token验证端口认证权限访问
                .checkTokenAccess("isAuthenticated()");
        
    }

    /**
     * 使用非对称加密算法来对Token进行签名
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {

        final JwtAccessTokenConverter converter = new JwtAccessToken();
        // 导入证书
        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "foobar".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("test"));

        return converter;
    }

    /**
     * 跨域
     * @return
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