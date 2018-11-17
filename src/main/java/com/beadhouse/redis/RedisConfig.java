package com.beadhouse.redis;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
/**
 * 
 * @notes 
 *
 * @author liujian
 * @date 2018年11月5日
 * @class RedisConfig.java
 */
@Configuration
public class RedisConfig {

	 //缓存管理器 spring boot 2.0后 配置缓存管理器 和2.0以前 不一样 根据自己的版本 配置
    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory redisTemplate) {
        return RedisCacheManager.create(redisTemplate);
    }
    // 以下两种redisTemplate自由根据场景选择
    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
    	 RedisTemplate<Object, Object> template = new RedisTemplate<>();
    	 template.setConnectionFactory(connectionFactory);
    	 //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值（默认使用JDK的序列化方式）
    	 Jackson2JsonRedisSerializer serializer = new Jackson2JsonRedisSerializer(Object.class);
    	 ObjectMapper mapper = new ObjectMapper();
    	 mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
         mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
         serializer.setObjectMapper(mapper);
         template.setValueSerializer(serializer);
       //使用StringRedisSerializer来序列化和反序列化redis的key值
         template.setKeySerializer(new StringRedisSerializer());
         template.afterPropertiesSet();
       //这里设置redis事务一致
         template.setEnableTransactionSupport(true);
    	 return template;
    }
    
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory factory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(factory);
        stringRedisTemplate.setEnableTransactionSupport(true);
        return stringRedisTemplate;
    }

}


  