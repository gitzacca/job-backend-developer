package br.com.intelipost.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.embedded.RedisServer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisServer redisServer() {
        RedisServer.builder().reset();
        return RedisServer.builder().port(6379).build();
    }

}
