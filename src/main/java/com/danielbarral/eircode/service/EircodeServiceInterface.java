package com.danielbarral.eircode.service;

import java.util.List;

import com.danielbarral.eircode.model.Address;

public interface EircodeServiceInterface {

	 public List<Address> irishLookup(String key, String search);
	
}
