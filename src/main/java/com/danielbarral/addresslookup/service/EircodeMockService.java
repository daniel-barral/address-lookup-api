package com.danielbarral.addresslookup.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.danielbarral.addresslookup.model.Address;

@Service
public class EircodeMockService implements AddressLookupServiceInterface {
	
    public List<Address> addressLookup(String key, String search) {
		
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
		
		Address address2 = new Address();
		address2.setAddressline1("Mock002 Addressline1");
		address2.setAddressline2("Mock002 Addressline2");
		address2.setSummaryline("Mock002 Summaryline");
		address2.setOrganisation("Mock002 Organisation");
		address2.setStreet("Mock002 Street");
		address2.setPosttown("Mock002 Posttown");
		address2.setCounty("Mock002 County");
		address2.setPostcode("Mock002 Postcode");
		
		addressList.add(address2);
		
        return addressList;
    }

}
