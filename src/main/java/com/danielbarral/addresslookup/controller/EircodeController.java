package com.danielbarral.addresslookup.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielbarral.addresslookup.service.EircodeService;

@RestController
public class EircodeController {
	
	@Value("${postcoderApiBaseUrl}")
	private String postcoderApiBaseUrl;
	
	@Value("${mockApiBaseUrl}")
	private String mockApiBaseUrl;
	
	@Value("${mock}")
	private boolean mock;
	
	@Inject
	private EircodeService eircodeService;
	
	@RequestMapping("/cache/{key}/{lookupType}/{countryCode}/{search}")
    public String irishAddressLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "lookupType") String lookupType,
    		@PathVariable(value = "countryCode") String countryCode,
    		@PathVariable(value = "search") String search) {
		
		String json = eircodeService.addressLookup(getApiBaseUrl(), key, "/" + lookupType + "/" + countryCode + "/" + search);
		
        return json;
    }
	
	private String getApiBaseUrl() {
		return mock ? mockApiBaseUrl : postcoderApiBaseUrl;
	}

}
