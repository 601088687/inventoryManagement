/*
package com.boot.template.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.exam.host}")
    private String examHost;
    @Value("${spring.redis.exam.port}")
    private int examPort;
    @Value("${spring.redis.exam.password}")
    private String examPassword;
    @Value("${spring.redis.jedis.pool.maxActive:10000}")
    private Integer maxActive;
    @Value("${spring.redis.jedis.pool.maxWait:6000}")
    private Integer maxWait;
    @Value("${spring.redis.jedis.pool.maxIdle:10000}")
    private Integer maxIdle;
    @Value("${spring.redis.jedis.pool.minIdle:100}")
    private Integer minIdle;
    @Value("${spring.redis.jedis.pool.testOnBorrow:false}")
    private boolean testOnBorrow;
    @Value("${spring.redis.jedis.pool.testOnReturn:false}")
    private boolean testOnReturn;
    @Value("${spring.redis.jedis.pool.connectionTimeout:60000}")
    private Integer connectionTimeout;


    @Bean
    public JedisPool examJedisPool() {
        JedisPoolConfig examConfig = new JedisPoolConfig();
        examConfig.setMaxIdle(maxIdle);
        examConfig.setMinIdle(minIdle);
        examConfig.setMaxTotal(maxActive);
        examConfig.setMaxWaitMillis(maxWait);
        examConfig.setTestOnReturn(testOnReturn);
        examConfig.setTestOnBorrow(testOnBorrow);
        return new JedisPool(examConfig, examHost, examPort, connectionTimeout, examPassword);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory lettuceConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        return redisTemplate;
    }
}
*/
