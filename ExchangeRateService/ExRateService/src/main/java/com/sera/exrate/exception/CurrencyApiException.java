package com.sera.exrate.exception;

public class CurrencyApiException extends RuntimeException{
	// 환율 정보 요청 실패시  발생
	
	private static final String msg = "API Exception, 환율 정보를 가져오지 못했습니다.";
	
	public CurrencyApiException() {
		super(msg);
	}
}
