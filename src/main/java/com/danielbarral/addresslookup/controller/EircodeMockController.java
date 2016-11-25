package com.danielbarral.addresslookup.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielbarral.addresslookup.model.Address;
import com.danielbarral.addresslookup.service.EircodeMockService;

@RestController
public class EircodeMockController {
	
	@Inject
	EircodeMockService eircodeMockService;
	
	@RequestMapping("/pcw/{key}/address/ie/{search}")
    public List<Address> irishLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
		List<Address> addressList = eircodeMockService.addressLookup(key, search);
		
        return addressList;
    }

}
