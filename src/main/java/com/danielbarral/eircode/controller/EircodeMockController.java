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
		address.setAddressline1("Department of Communications, Energy and Natural Resources");
		address.setAddressline2("Adelaide Road");
		address.setSummaryline("Department of Communications, Energy and Natural Resources, Adelaide Road, Dublin 2, D02 X285");
		address.setOrganisation("Department of Communications, Energy and Natural Resources");
		address.setStreet("Adelaide Road");
		address.setPosttown("Dublin 2");
		address.setCounty("Dublin");
		address.setPostcode("D02 X285");
		
		addressList.add(address);
		
        return addressList;
    }

}
