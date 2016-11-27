package com.danielbarral.addresslookup;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class UrlBuilderTest {
	
	@Test
    public void testExampleUrl() {
		
		Map<String, String[]> parameterMap = new HashMap<String, String[]>();
		parameterMap.put("lines", new String[]{"3"});
		parameterMap.put("format", new String[]{"json"});
		
		UrlBuilder urlBuilder = new UrlBuilder()
				.baseUrl("http://ws.postcoder.com/pcw/")
				.apiKey("PCW45-12345-12345-1234X")
				.lookupType("address")
				.countryCode("ie")
				.queryString("D02X285")
				.parameterMap(parameterMap);
		
		assertEquals(
				"http://ws.postcoder.com/pcw/PCW45-12345-12345-1234X/address/ie/D02X285?format=json&lines=3", 
				urlBuilder.buildFullUrl());
		
		assertEquals(
				"address/ie/D02X285?format=json&lines=3", 
				urlBuilder.buildCacheKey());
		
	}

}
