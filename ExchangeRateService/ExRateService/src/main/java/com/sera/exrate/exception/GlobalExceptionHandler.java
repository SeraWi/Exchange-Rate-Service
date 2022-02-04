package com.sera.exrate.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import com.sera.exrate.domain.CurrencyResponseDto;
import com.sera.exrate.service.CurrencyApiService;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
	private final static Logger logger = LoggerFactory.getLogger(CurrencyApiService.class);
	
	@ExceptionHandler(value = Exception.class)
	public ErrorResponse handleException(Exception ex){
		return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
	}

	@ExceptionHandler(value = NullPointerException.class)
	public ErrorResponse handleNullPointerException(NullPointerException ex){
		logger.error("nullpointerException 발생");
		return ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,ex.getMessage());
	}
	
		
	@ExceptionHandler(value =CurrencyApiException.class)
	public CurrencyResponseDto handleCurrencyApiException(CurrencyApiException ex){
		logger.error("apiEXception 발생");
		CurrencyResponseDto res = new CurrencyResponseDto(0.0,false, ex.getMessage());
		return res;
	}
	
}
