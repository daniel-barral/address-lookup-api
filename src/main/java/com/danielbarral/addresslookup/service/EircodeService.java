package com.danielbarral.addresslookup.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danielbarral.addresslookup.UrlBuilder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class EircodeService {

	@Inject
	private JedisPool jedisPool;
	
	private RestTemplate restTemplate = new RestTemplate();

	public String addressLookup(String baseUrl, String key, String queryString) {

		String url = new UrlBuilder().baseUrl(baseUrl).apiKey(key).queryString(queryString).build();

		try (Jedis jedis = jedisPool.getResource()) {

			String jsonCache = jedis.get(url);
			if (jsonCache != null) { //cache hit
				return jsonCache;
			}
			
			String json = restTemplate.getForObject(url, String.class);

			jedis.set(url, json);

			return json;
		}

	}

}
