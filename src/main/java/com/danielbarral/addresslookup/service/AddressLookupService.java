package com.danielbarral.addresslookup.service;

import java.util.Map;

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

	public String addressLookup(String baseUrl, String key, String lookupType, String countryCode, 
			String queryString, String latitude, String longitude, Map<String, String[]> parameterMap) {

		UrlBuilder urlBuilder = new UrlBuilder()
				.baseUrl(baseUrl)
				.apiKey(key)
				.lookupType(lookupType)
				.countryCode(countryCode)
				.queryString(queryString)
				.latitude(latitude)
				.longitude(longitude)
				.parameterMap(parameterMap);
		
		String fullUrl = urlBuilder.buildFullUrl();
		String cacheKey = urlBuilder.buildCacheKey();
		
		logger.debug("Address lookup request URL: {}", fullUrl);

		try (Jedis jedis = jedisPool.getResource()) {

			String jsonCache = jedis.get(cacheKey);
			if (jsonCache != null) { //cache hit
				logger.debug("Cache hit for URL: {}", fullUrl);
				return jsonCache;
			}
			
			logger.debug("Cache miss for URL: {}", fullUrl);
			
			String json = restTemplate.getForObject(fullUrl, String.class);

			jedis.set(cacheKey, json);

			return json;
		}

	}

}
