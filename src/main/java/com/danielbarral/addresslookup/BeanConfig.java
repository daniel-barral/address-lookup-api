package com.danielbarral.addresslookup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class BeanConfig {
	
	@Value("${jedisServer}")
	private String jedisServer;
	
	@Value("${jedisPort}")
	private Integer jedisPort;
	
	@Bean
	public JedisPool jedisPool() {
		return new JedisPool(new JedisPoolConfig(), jedisServer, jedisPort);
	}

}
