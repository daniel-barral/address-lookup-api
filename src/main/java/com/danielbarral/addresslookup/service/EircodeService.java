package com.danielbarral.addresslookup.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danielbarral.addresslookup.UrlBuilder;

@Service
public class EircodeService {
	
    public String addressLookup(String baseUrl, String key, String queryString) {
		
    	String url = new UrlBuilder().baseUrl(baseUrl).apiKey(key).queryString(queryString).build();
    	
		RestTemplate restTemplate = new RestTemplate();
		String json = restTemplate.getForObject(url, String.class);
		
        return json;
    }

}
