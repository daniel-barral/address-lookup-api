package com.danielbarral.eircode.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielbarral.eircode.model.Address;
import com.danielbarral.eircode.service.EircodeService;

@RestController
public class EircodeController {
	
	@Inject
	EircodeService eircodeService;
	
	@RequestMapping("/cache/{key}/address/ie/{search}")
    public List<Address> irishLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
		List<Address> addressList = eircodeService.irishLookup(key, search);
		
        return addressList;
    }
	
	@RequestMapping("/cache/{key}/address/uk/{search}")
    public String ukLookup(@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
        return "ukLookup key " + key + " search " + search;
    }

}
