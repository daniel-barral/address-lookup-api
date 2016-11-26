package com.danielbarral.addresslookup;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class BeanConfig {
	
	@Bean
	public JedisPool jedisPool() {
		return new JedisPool(new JedisPoolConfig(), "localhost");
	}

}
