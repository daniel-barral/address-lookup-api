package com.danielbarral.eircode.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EircodeController {
	
	@RequestMapping("/pcw/{key}/address/ie/{search}")
    public String irishLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
        return "irishLookup key " + key + " search " + search;
    }
	
	@RequestMapping("/pcw/{key}/address/uk/{search}")
    public String ukLookup(@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
        return "ukLookup key " + key + " search " + search;
    }

}
