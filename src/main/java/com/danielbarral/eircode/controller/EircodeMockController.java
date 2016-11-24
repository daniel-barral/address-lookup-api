package com.danielbarral.eircode.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielbarral.eircode.model.Address;

@RestController
public class EircodeMockController {
	
	@RequestMapping("/pcw/{key}/address/ie/{search}")
    public List<Address> irishLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
		List<Address> addressList = new ArrayList<Address>();
		
		Address address = new Address();
		address.setAddressline1("Mock Addressline1");
		address.setAddressline2("Mock Addressline2");
		address.setSummaryline("Mock Summaryline");
		address.setOrganisation("Mock Organisation");
		address.setStreet("Mock Street");
		address.setPosttown("Mock Posttown");
		address.setCounty("Mock County");
		address.setPostcode("Mock Postcode");
		
		addressList.add(address);
		
        return addressList;
    }

}
