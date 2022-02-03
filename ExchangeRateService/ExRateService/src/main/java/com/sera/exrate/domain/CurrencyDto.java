package com.sera.exrate.domain;

import java.util.Map;


public class CurrencyDto {
	
	private boolean success;
	private String source;
	private int timestamp; 
	private Map<String,Double> quotes; 
	private Map<String,String> error;
	
	public CurrencyDto() {}
	
	public CurrencyDto(boolean success, String source, int timestamp, Map<String, Double> quotes,
			Map<String, String> error) {
		super();
		this.success = success;
		this.source = source;
		this.timestamp = timestamp;
		this.quotes = quotes;
		this.error = error;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}

	public Map<String, Double> getQuotes() {
		return quotes;
	}

	public void setQuotes(Map<String, Double> quotes) {
		this.quotes = quotes;
	}

	public Map<String, String> getError() {
		return error;
	}

	public void setError(Map<String, String> error) {
		this.error = error;
	}
	
	
	
}
