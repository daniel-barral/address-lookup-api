package com.danielbarral.addresslookup.service;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.danielbarral.addresslookup.model.Address;

@Service
public class EircodeService implements AddressLookupServiceInterface {
	
    public List<Address> addressLookup(String key, String search) {
		
		String url = "http://ws.postcoder.com/pcw/PCW45-12345-12345-1234X/address/ie/D02X285?lines=3&format=json";
		//String url = "http://localhost:8080/pcw/key/address/ie/search";
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<Address>> response = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<Address>>(){});
		List<Address> addressList = response.getBody();
		
        return addressList;
    }

}
