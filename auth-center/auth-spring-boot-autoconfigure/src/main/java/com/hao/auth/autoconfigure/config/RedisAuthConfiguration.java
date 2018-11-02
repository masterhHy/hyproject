package com.hao.auth.autoconfigure.config;

import com.hao.auth.autoconfigure.redis.RedisObjectSerializer;
import com.hao.remote.api.userservice.entity.RemoteSysAuthority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis配置类
 */
@Configuration
public class RedisAuthConfiguration {

    @Autowired
    private RedisConnectionFactory factory;


    @Bean
    public RedisTemplate<String, RemoteSysAuthority> baseModelTemplate() {
        RedisTemplate<String, RemoteSysAuthority> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
