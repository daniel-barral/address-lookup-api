package com.danielbarral.addresslookup;

public class UrlBuilder {
	
	private String baseUrl;
	private String apiKey;
	private String queryString;
	
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
	
	public String build() {
		String url = baseUrl + apiKey + queryString;
		return url;
	}

}
