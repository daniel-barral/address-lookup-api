package com.danielbarral.eircode.controller;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.danielbarral.eircode.model.Address;

@RestController
public class EircodeController {
	
	@RequestMapping("/cache/{key}/address/ie/{search}")
    public List<Address> irishLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
		//String url = "http://ws.postcoder.com/pcw/PCW45-12345-12345-1234X/address/ie/D02X285?lines=3&format=json";
		String url = "http://localhost:8080/pcw/key/address/ie/search";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Address>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Address>>(){});
		List<Address> addressList = response.getBody();
		
        return addressList;
    }
	
	@RequestMapping("/cache/{key}/address/uk/{search}")
    public String ukLookup(@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
        return "ukLookup key " + key + " search " + search;
    }

}
