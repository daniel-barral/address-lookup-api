package com.danielbarral.addresslookup;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UrlBuilder {
	
	private String baseUrl;
	private String apiKey;
	private String lookupType;
	private String countryCode;
	private String queryString;
	private String latitude;
	private String longitude;
	private Map<String, String[]> parameterMap;
	
	public UrlBuilder baseUrl(String baseUrl) {
		this.baseUrl  = baseUrl;
		return this;
	}
	
	public UrlBuilder apiKey(String apiKey) {
		this.apiKey = apiKey;
		return this;
	}
	
	public UrlBuilder queryString(String queryString) {
		this.queryString  = queryString;
		return this;
	}
	
	public UrlBuilder lookupType(String lookupType) {
		this.lookupType  = lookupType;
		return this;
	}
	
	public UrlBuilder countryCode(String countryCode) {
		this.countryCode  = countryCode;
		return this;
	}
	
	public UrlBuilder latitude(String latitude) {
		this.latitude  = latitude;
		return this;
	}
	
	public UrlBuilder longitude(String longitude) {
		this.longitude  = longitude;
		return this;
	}
	
	public UrlBuilder parameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap  = parameterMap;
		return this;
	}
	
	public String buildFullUrl() {
		return baseUrl + apiKey + "/" + buildCacheKey();
	}
	
	public String buildCacheKey() {
		String url = lookupType + "/" + countryCode + "/";
		
		if (lookupType.equals("rgeo")) {
			url += latitude + "/" + longitude;
		} else {
			url += queryString;
		}
		
		Set<String> keySet = parameterMap.keySet();
		
		List<String> sortedParams = keySet.stream().sorted().collect(Collectors.toList());
		
		boolean first = true;
		for (String parameterKey : sortedParams) {
			for (String value : parameterMap.get(parameterKey)) {
				url += first ? "?" : "&";
				url += parameterKey + "=" + value;
				first = false;
			}
		}
		
		return url;
	}

}
