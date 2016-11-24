package com.danielbarral.eircode.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class EircodeController {
	
	@RequestMapping("/cache/{key}/address/ie/{search}")
    public String irishLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
		String url = "http://ws.postcoder.com/pcw/PCW45-12345-12345-1234X/address/ie/D02X285?lines=3&format=json";
		RestTemplate restTemplate = new RestTemplate();
		String json = restTemplate.getForObject(url, String.class);
		
        return json;
    }
	
	@RequestMapping("/cache/{key}/address/uk/{search}")
    public String ukLookup(@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
        return "ukLookup key " + key + " search " + search;
    }

}
