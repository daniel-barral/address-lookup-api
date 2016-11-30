package com.danielbarral.addresslookup.controller;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import java.util.Set;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AddressLookupMockControllerTest {
	
	@Value("${postcoderApiBaseUrl}")
	private String postcoderApiBaseUrl;
	
	@Value("${mockApiBaseUrl}")
	private String mockApiBaseUrl;
	
	@Inject
	private TestRestTemplate restTemplate;
	
	@Test
	public void irishAddressLookupTest() {
		String url = "/mock/PCW45-12345-12345-1234X/address/ie/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals(true, body.contains("Mock Addressline1"));
	}
	
	@Test
	public void irishAddressAndCoordinateLookupTest() {
		String url = "/mock/PCW45-12345-12345-1234X/addressgeo/ie/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals(true, body.contains("Mock Addressline1"));
		assertEquals(true, body.contains("53.332067"));
	}
	
	@Test
	public void irishCoordinateLookupTest() {
		String url = "/mock/PCW45-12345-12345-1234X/position/ie/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals(true, body.contains("53.332067"));
	}
	
	@Test
	public void reverseGeocodeTest() {
		String url = "/mock/PCW45-12345-12345-1234X/rgeo/ie/12.345678/-1.234567?distance=50";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals(true, body.contains("Mock Reverse geocode Addressline1"));
		assertEquals(true, body.contains("Mock002 Reverse geocode Addressline1"));
	}
	
	@Test
	public void ukAddressLookupTest() {
		String url = "/mock/PCW45-12345-12345-1234X/address/uk/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals(true, body.contains("Mock UK Addressline1"));
		assertEquals(true, body.contains("Mock002 UK Postcode"));
	}
	
	@Test
	public void ukAddressAndCoordinateLookupTest() {
		String url = "/mock/PCW45-12345-12345-1234X/addressgeo/uk/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals(true, body.contains("Mock UK Addressline1"));
		assertEquals(true, body.contains("Mock002 UK Postcode"));
		assertEquals(true, body.contains("-6.255492"));
	}
	
	@Test
	public void ukCoordinateLookupTest() {
		String url = "/mock/PCW45-12345-12345-1234X/position/uk/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals(true, body.contains("53.332067"));
		assertEquals(true, body.contains("12.345678"));
	}
	

}
