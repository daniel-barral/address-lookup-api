package com.danielbarral.addresslookup.controller;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AddressLookupControllerTest {
	
	@Value("${postcoderApiBaseUrl}")
	private String postcoderApiBaseUrl;
	
	@Value("${mockApiBaseUrl}")
	private String mockApiBaseUrl;
	
	@Inject
	private TestRestTemplate restTemplate;
	
	@Inject
    private MockMvc mvc;
	
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
	public void exampleIrishAddresTest() {
		String url = "/proxy/PCW45-12345-12345-1234X/address/ie/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals("[{\"summaryline\":\"Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285\",\"organisation\":\"Dept of Communications, Climate Change and Natural Resources\",\"number\":\"29-31\",\"premise\":\"29-31\",\"street\":\"Adelaide Road\",\"posttown\":\"Dublin 2\",\"county\":\"Dublin\",\"postcode\":\"D02 X285\"}]", body);
	}
	
	@Test
	public void exampleIrishAddressgeoTest() {
		String url = "/proxy/PCW45-12345-12345-1234X/addressgeo/ie/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals("[{\"summaryline\":\"Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285\",\"organisation\":\"Dept of Communications, Climate Change and Natural Resources\",\"number\":\"29-31\",\"premise\":\"29-31\",\"street\":\"Adelaide Road\",\"posttown\":\"Dublin 2\",\"county\":\"Dublin\",\"postcode\":\"D02 X285\",\"latitude\":\"53.332067\",\"longitude\":\"-6.255492\"}]", body);
	}
	
	@Test
	public void exampleIrishCoordinateTest() {
		String url = "/proxy/PCW45-12345-12345-1234X/position/ie/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals("[{\"latitude\":\"53.332067\",\"longitude\":\"-6.255492\"}]", body);
	}
	
	@Test
	public void exampleIrishReverseGeocodeTest() throws Exception {
		String url = "/proxy/PCW45-12345-12345-1234X/rgeo/ie/53.332067/-6.255492?distance=50&format=json";
		
		MvcResult mvcResult = mvc.perform(get(url))
				.andExpect(status().isOk())
				.andReturn();
		
		String response = mvcResult.getResponse().getContentAsString();
		
		assertEquals("[{\"summaryline\":\"Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285\",\"organisation\":\"Dept of Communications, Climate Change and Natural Resources\",\"number\":\"29-31\",\"premise\":\"29-31\",\"street\":\"Adelaide Road\",\"posttown\":\"Dublin 2\",\"county\":\"Dublin\",\"postcode\":\"D02 X285\"}]", response);
		
	}
	
	
	@Test
	public void swapParameterOrderCacheTest() throws Exception {
		
		//Two URL identical except for parameter order
		testIfSecondUrlCameFromCache(
				"address/ie/d02x285?format=json&lines=3",
				"address/ie/d02x285?lines=3&format=json");
		
	}
	
	@Test
	public void mixedCasePoscodeCacheTest() throws Exception {
		
		//Two URL identical except for uppercase/lowercase postcodes
		testIfSecondUrlCameFromCache(
				"address/ie/d02x285?format=json",
				"address/ie/D02X285?format=json");
		
	}
	
	private void testIfSecondUrlCameFromCache(String urlFragment1, String urlFragment2) throws Exception {
		
		try (Jedis jedis = jedisPool.getResource()) {
			
			clearCache();
			
			String dummyJson = "dummy-json" + random.nextInt();
			
			jedis.set(urlFragment1, dummyJson); //put a "dummy-json" in the cache
			
			String url2 = "/proxy/PCW45-12345-12345-1234X/" + urlFragment2;
			
			mvc.perform(get(url2))
					.andExpect(status().isOk())
					.andExpect(content().string(dummyJson));
			
		}
		
	}

}
