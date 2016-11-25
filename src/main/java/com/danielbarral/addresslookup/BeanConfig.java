package com.danielbarral.addresslookup;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.danielbarral.addresslookup.service.EircodeMockService;
import com.danielbarral.addresslookup.service.EircodeService;
import com.danielbarral.addresslookup.service.AddressLookupServiceInterface;

@Configuration
public class BeanConfig {
	
	@Value("${mockEircodeApi}")
	private Boolean mockEircodeApi;

	@Bean
	public AddressLookupServiceInterface eircodeService() {
		if (mockEircodeApi) {
			return new EircodeMockService();
		} else {
			return new EircodeService();
		}
	}

}
