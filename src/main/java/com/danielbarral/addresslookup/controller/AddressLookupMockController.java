package com.danielbarral.addresslookup.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.danielbarral.addresslookup.model.Address;
import com.danielbarral.addresslookup.model.AddressGeo;
import com.danielbarral.addresslookup.model.Coordinate;

@RestController
public class AddressLookupMockController {
	
	@RequestMapping("/mock/{key}/address/ie/{search}")
    public List<Address> irishAddressLookup(
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
	
	@RequestMapping("/mock/{key}/addressgeo/ie/{search}")
    public List<AddressGeo> irishAddressAndCoordinateLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
		List<AddressGeo> addressList = new ArrayList<AddressGeo>();
		
		AddressGeo address = new AddressGeo();
		address.setAddressline1("Mock Addressline1");
		address.setAddressline2("Mock Addressline2");
		address.setSummaryline("Mock Summaryline");
		address.setOrganisation("Mock Organisation");
		address.setStreet("Mock Street");
		address.setPosttown("Mock Posttown");
		address.setCounty("Mock County");
		address.setPostcode("Mock Postcode");
		address.setLatitude("53.332067");
		address.setLongitude("-6.255492");
		
		addressList.add(address);
		
		AddressGeo address2 = new AddressGeo();
		address2.setAddressline1("Mock002 Addressline1");
		address2.setAddressline2("Mock002 Addressline2");
		address2.setSummaryline("Mock002 Summaryline");
		address2.setOrganisation("Mock002 Organisation");
		address2.setStreet("Mock002 Street");
		address2.setPosttown("Mock002 Posttown");
		address2.setCounty("Mock002 County");
		address2.setPostcode("Mock002 Postcode");
		address2.setLatitude("12.345678");
		address2.setLongitude("-1.234567");
		
		addressList.add(address2);
        
		return addressList;
    }
	
	@RequestMapping("/mock/{key}/position/ie/{search}")
    public List<Coordinate> irishCoordinateLookup(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "search") String search) {
		
		List<Coordinate> coordinateList = new ArrayList<Coordinate>();
		
		Coordinate coordinate = new Coordinate();
		coordinate.setLatitude("53.332067");
		coordinate.setLongitude("-6.255492");
		
		coordinateList.add(coordinate);
		
		Coordinate coordinate2 = new Coordinate();
		coordinate2.setLatitude("12.345678");
		coordinate2.setLongitude("-1.234567");
		
		coordinateList.add(coordinate2);
        
		return coordinateList;
    }
	
	@RequestMapping("/mock/{key}/rgeo/ie/{latitude}/{longitude}")
    public List<Address> reverseGeocode(
    		@PathVariable(value = "key") String key,
    		@PathVariable(value = "latitude") String latitude,
    		@PathVariable(value = "latitude") String longitude,
    		@RequestParam(value="distance", required=true) String distance) {
		
		List<Address> addressList = new ArrayList<Address>();
		
		Address address = new Address();
		address.setAddressline1("Mock Reverse geocode Addressline1");
		address.setAddressline2("Mock Reverse geocode Addressline2");
		address.setSummaryline("Mock Reverse geocode Summaryline");
		address.setOrganisation("Mock Reverse geocode Organisation");
		address.setStreet("Mock Reverse geocode Street");
		address.setPosttown("Mock Reverse geocode Posttown");
		address.setCounty("Mock Reverse geocode County");
		address.setPostcode("Mock Reverse geocode Postcode");
		
		addressList.add(address);
		
		Address address2 = new Address();
		address2.setAddressline1("Mock002 Reverse geocode Addressline1");
		address2.setAddressline2("Mock002 Reverse geocode Addressline2");
		address2.setSummaryline("Mock002 Reverse geocode Summaryline");
		address2.setOrganisation("Mock002 Reverse geocode Organisation");
		address2.setStreet("Mock002 Reverse geocode Street");
		address2.setPosttown("Mock002 Reverse geocode Posttown");
		address2.setCounty("Mock002 Reverse geocode County");
		address2.setPostcode("Mock002 Reverse geocode Postcode");
		
		addressList.add(address2);
        
		return addressList;
    }

}
