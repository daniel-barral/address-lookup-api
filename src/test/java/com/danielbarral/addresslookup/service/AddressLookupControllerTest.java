package com.danielbarral.addresslookup.service;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressLookupControllerTest {
	
	@Value("${postcoderApiBaseUrl}")
	private String postcoderApiBaseUrl;
	
	@Value("${mockApiBaseUrl}")
	private String mockApiBaseUrl;
	
	@Inject
	private TestRestTemplate restTemplate;
	
	
	@Test
	public void exampleIrishAddresTest() {
		String url = postcoderApiBaseUrl + "PCW45-12345-12345-1234X/address/ie/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals("[{\"summaryline\":\"Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285\",\"organisation\":\"Dept of Communications, Climate Change and Natural Resources\",\"number\":\"29-31\",\"premise\":\"29-31\",\"street\":\"Adelaide Road\",\"posttown\":\"Dublin 2\",\"county\":\"Dublin\",\"postcode\":\"D02 X285\"}]", body);
	}
	
	@Test
	public void exampleIrishAddressgeoTest() {
		String url = postcoderApiBaseUrl + "PCW45-12345-12345-1234X/addressgeo/ie/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals("[{\"summaryline\":\"Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285\",\"organisation\":\"Dept of Communications, Climate Change and Natural Resources\",\"number\":\"29-31\",\"premise\":\"29-31\",\"street\":\"Adelaide Road\",\"posttown\":\"Dublin 2\",\"county\":\"Dublin\",\"postcode\":\"D02 X285\",\"latitude\":\"53.332067\",\"longitude\":\"-6.255492\"}]", body);
	}
	
	@Test
	public void exampleIrishCoordinateTest() {
		String url = postcoderApiBaseUrl + "PCW45-12345-12345-1234X/position/ie/D02X285?format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		assertEquals("[{\"latitude\":\"53.332067\",\"longitude\":\"-6.255492\"}]", body);
	}
	
	@Test
	public void exampleIrishReverseGeocodeTest() {
		String url = postcoderApiBaseUrl + "PCW45-12345-12345-1234X/rgeo/ie/53.332067/-6.255492?distance=50&format=json";
		String body = this.restTemplate.getForObject(url, String.class);
		boolean startsWith = body.startsWith("[{\"summaryline\":\"Irish Pension Trust, Marsh House, 25-28 Adelaide Road, Dublin 2, D02 RY98\",\"organisation\":\"Irish Pension Trust\",\"buildingname\":\"Marsh House\",\"number\":\"25-28\",\"premise\":\"Marsh House, 25-28\",\"street\":\"Adelaide Road\",\"posttown\":\"Dublin 2\",\"county\":\"Dublin\",\"postcode\":\"D02 RY98\"},{\"summaryline\":\"Dept of Communications, Climate Change and Natural Resources, 29-31 Adelaide Road, Dublin 2, D02 X285\",\"organisation\":\"Dept of Communications, Climate Change and Natural Resources\",\"number\":\"29-31\",\"premise\":\"29-31\",\"street\":\"Adelaide Road\",\"posttown\":\"Dublin 2\",\"county\":\"Dublin\",\"postcode\":\"D02 X285\"}");
		assertEquals(true, startsWith);
	}

}
