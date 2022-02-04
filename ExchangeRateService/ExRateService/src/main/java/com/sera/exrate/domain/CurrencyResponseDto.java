package com.sera.exrate.domain;

public class CurrencyResponseDto {
	private double currency;
	private boolean success;
	private String msg;
	
	public CurrencyResponseDto(){};
	
	public CurrencyResponseDto(double currency, boolean success, String msg) {
		super();
		this.currency = currency;
		this.success = success;
		this.msg = msg;
	}
	public double getCurrency() {
		return currency;
	}
	public void setCurrency(double currency) {
		this.currency = currency;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
