package com.danielbarral.addresslookup.service;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.json.JSONArray;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EircodeServiceTest {
	
	@Value("${postcoderApiBaseUrl}")
	private String postcoderApiBaseUrl;
	
	@Value("${mockApiBaseUrl}")
	private String mockApiBaseUrl;
	
	@Inject
	private EircodeService eircodeService;
	
	@Test
    public void testExampleResult() {
		
    	String json = eircodeService.addressLookup(postcoderApiBaseUrl, "PCW45-12345-12345-1234X", "/address/ie/D02X285?lines=3&format=json");
    	
    	JSONArray addresses = new JSONArray(json);
    	
    	assertEquals(1, addresses.length());
    	
    	assertEquals("D02 X285", addresses.getJSONObject(0).get("postcode"));
    	assertEquals("Dublin", addresses.getJSONObject(0).get("county"));
    	assertEquals("Adelaide Road", addresses.getJSONObject(0).get("street"));
		
    }
	
	@Test
    public void testCachedResult() {
		
    	//List<Address> addresses = eircodeService.irishLookup("key", "searchString");
    	
    	//TODO: check if result came from cache
		
    }

}
