package com.danielbarral.eircode.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielbarral.eircode.model.Address;
import com.danielbarral.eircode.service.EircodeMockService;

@RestController
public class EircodeMockController {
	
	@Inject
	EircodeMockService eircodeMockService;
	
	@RequestMapping("/pcw/{key}/address/ie/{search}")
    public List<Address> irishLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
		List<Address> addressList = eircodeMockService.irishLookup(key, search);
		
        return addressList;
    }

}
