package com.danielbarral.addresslookup.service;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danielbarral.addresslookup.UrlBuilder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class AddressLookupService {

	@Inject
	private JedisPool jedisPool;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private RestTemplate restTemplate = new RestTemplate();

	public String addressLookup(String baseUrl, String key, String queryString) {

		String url = new UrlBuilder().baseUrl(baseUrl).apiKey(key).queryString(queryString).build();
		
		logger.debug("Address lookup request URL: {}", url);

		try (Jedis jedis = jedisPool.getResource()) {

			String jsonCache = jedis.get(url);
			if (jsonCache != null) { //cache hit
				logger.debug("Cache hit for URL: {}", url);
				return jsonCache;
			}
			
			logger.debug("Cache miss for URL: {}", url);
			
			String json = restTemplate.getForObject(url, String.class);

			jedis.set(url, json);

			return json;
		}

	}

}
