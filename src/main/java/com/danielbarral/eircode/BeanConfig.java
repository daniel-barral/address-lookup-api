package com.danielbarral.eircode;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.danielbarral.eircode.service.EircodeMockService;
import com.danielbarral.eircode.service.EircodeService;
import com.danielbarral.eircode.service.EircodeServiceInterface;

@Configuration
public class BeanConfig {
	
	@Value("${mockEircodeApi}")
	private Boolean mockEircodeApi;

	@Bean
	public EircodeServiceInterface eircodeService() {
		if (mockEircodeApi) {
			return new EircodeMockService();
		} else {
			return new EircodeService();
		}
	}

}
