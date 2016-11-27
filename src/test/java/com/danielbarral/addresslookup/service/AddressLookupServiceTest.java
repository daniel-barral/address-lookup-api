package com.danielbarral.addresslookup.service;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.inject.Inject;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.danielbarral.addresslookup.UrlBuilder;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressLookupServiceTest {
	
	@Value("${postcoderApiBaseUrl}")
	private String postcoderApiBaseUrl;
	
	@Value("${mockApiBaseUrl}")
	private String mockApiBaseUrl;
	
	@Inject
	private AddressLookupService addressLookupService;
	
	@Inject
	private JedisPool jedisPool;
	
	private Random random = new Random();
	
	
	private void clearCache() {
		try (Jedis jedis = jedisPool.getResource()) {
			Set<String> keys = jedis.keys("*");
			for (String key : keys) {
			    jedis.del(key);
			}
		}
	}
	
	@Test
    public void testExampleResult() {
		
		Map<String, String[]> parameterMap = new HashMap<String, String[]>();
		parameterMap.put("lines", new String[]{"3"});
		parameterMap.put("format", new String[]{"json"});
		
		clearCache(); //ensure result won't come from cache
		
    	String json = addressLookupService.addressLookup(postcoderApiBaseUrl, "PCW45-12345-12345-1234X", "address", "ie", "D02X285", null, null, parameterMap);
    	
    	JSONArray addresses = new JSONArray(json);
    	
    	assertEquals(1, addresses.length());
    	
    	assertEquals("D02 X285", addresses.getJSONObject(0).get("postcode"));
    	assertEquals("Dublin", addresses.getJSONObject(0).get("county"));
    	assertEquals("Adelaide Road", addresses.getJSONObject(0).get("street"));
		
    }
	
	@Test
    public void testCachedResult() {
		
		try (Jedis jedis = jedisPool.getResource()) {
			
			String baseUrl = postcoderApiBaseUrl;
			String key = "PCW45-12345-12345-1234X";
			String lookupType = "address";
			String countryCode = "ie";
			String queryString = "D02X285";
			
			Map<String, String[]> parameterMap = new HashMap<String, String[]>();
			parameterMap.put("lines", new String[]{"3"});
			parameterMap.put("format", new String[]{"json"});
			
			String cacheKey = new UrlBuilder()
					.baseUrl(baseUrl)
					.apiKey(key)
					.lookupType(lookupType)
					.countryCode(countryCode)
					.queryString(queryString)
					.parameterMap(parameterMap)
					.buildCacheKey();
			
			String dummyJson = "dummy-json" + random.nextInt();
			
			jedis.set(cacheKey, dummyJson); //put a "dummy-json" in the cache
			
			String json = addressLookupService.addressLookup(baseUrl, key, lookupType, countryCode, queryString, null, null, parameterMap);
			
			assertEquals(dummyJson, json); //check if result came from the cache
			
			jedis.del(cacheKey); //remove the dummy to avoid trash
			
		}
		
    }

}
