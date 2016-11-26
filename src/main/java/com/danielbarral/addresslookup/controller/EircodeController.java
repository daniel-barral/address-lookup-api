package com.danielbarral.addresslookup.controller;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danielbarral.addresslookup.service.EircodeService;

@RestController
public class EircodeController {
	
	@Inject
	private String apiBaseUrl;
	
	@Inject
	private EircodeService eircodeService;
	
	@RequestMapping("/cache/{key}/{lookupType}/{countryCode}/{search}")
    public String irishAddressLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "lookupType") String lookupType,
    		@PathVariable(value = "countryCode") String countryCode,
    		@PathVariable(value = "search") String search) {
		
		String json = eircodeService.addressLookup(apiBaseUrl, key, "/" + lookupType + "/" + countryCode + "/" + search);
		
        return json;
    }
	
	@RequestMapping("/cache/{key}/rgeo/ie/{latitude}/{longitude}")
    public String reverseGeocode(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "latitude") String latitude,
    		@PathVariable(value = "longitude") String longitude,
    		@RequestParam(value="distance", required=true) String distance) {
		
		String json = eircodeService.addressLookup(apiBaseUrl, key, "/rgeo/ie/" + latitude + "/" + longitude + "?distance=" + distance);
		
        return json;
    }

}
