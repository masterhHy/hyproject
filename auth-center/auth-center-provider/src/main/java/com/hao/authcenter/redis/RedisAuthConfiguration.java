package com.hao.authcenter.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.hao.user.entity.SysAuthority;
import com.hao.user.entity.SysRole;

@Configuration
public class RedisAuthConfiguration {
	@Autowired
	private RedisConnectionFactory factory;
	

    @Bean
    public RedisTemplate<String, SysRole> baseRoleTemplate() {
        RedisTemplate<String, SysRole> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    @Bean
    public RedisTemplate<String, SysAuthority> baseModelTemplate() {
        RedisTemplate<String, SysAuthority> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }
}
