package com.danielbarral.addresslookup.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.danielbarral.addresslookup.model.Address;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EircodeServiceTest {
	
	@Inject
	private EircodeService eircodeService;
	
	@Test
    public void testExampleResult() {
		
    	List<Address> addresses = eircodeService.addressLookup("key", "D02X285");
    	
    	assertEquals(1, addresses.size());
    	
    	Address address = addresses.get(0);
    	
    	assertEquals("D02 X285", address.getPostcode());
    	assertEquals("Dublin", address.getCounty());
    	assertEquals("Adelaide Road", address.getStreet());
		
    }
	
	@Test
    public void testCachedResult() {
		
    	//List<Address> addresses = eircodeService.irishLookup("key", "searchString");
    	
    	//TODO: check if result came from cache
		
    }

}
