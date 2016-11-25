package com.danielbarral.addresslookup.service;

import java.util.List;

import com.danielbarral.addresslookup.model.Address;

public interface AddressLookupServiceInterface {

	 public List<Address> addressLookup(String key, String search);
	
}
