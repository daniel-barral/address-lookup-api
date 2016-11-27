package com.danielbarral.addresslookup.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danielbarral.addresslookup.service.AddressLookupService;

@RestController
public class AddressLookupController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Inject
	private String apiBaseUrl;
	
	@Inject
	private AddressLookupService addressLookupService;
	
	@RequestMapping("/proxy/{key}/{lookupType}/{countryCode}/{search}")
    public String addressLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "lookupType") String lookupType,
    		@PathVariable(value = "countryCode") String countryCode,
    		@PathVariable(value = "search") String search) {
		
		logger.debug("Address lookup with key {} lookupType {} coutryCode {} search {}", key, lookupType, countryCode, search);
		
		String json = addressLookupService.addressLookup(apiBaseUrl, key, "/" + lookupType + "/" + countryCode + "/" + search);
		
        return json;
    }
	
	@RequestMapping("/proxy/{key}/rgeo/ie/{latitude}/{longitude}")
    public String reverseGeocode(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "latitude") String latitude,
    		@PathVariable(value = "longitude") String longitude,
    		@RequestParam(value="distance", required=true) String distance) {
		
		logger.debug("Reverse geocode with key {} latitude {} longitude {} distance {}", key, latitude, longitude, distance);
		
		String json = addressLookupService.addressLookup(apiBaseUrl, key, "/rgeo/ie/" + latitude + "/" + longitude + "?distance=" + distance);
		
        return json;
    }

}
