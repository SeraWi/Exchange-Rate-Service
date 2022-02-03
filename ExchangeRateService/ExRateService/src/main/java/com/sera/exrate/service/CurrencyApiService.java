package com.sera.exrate.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import com.sera.exrate.domain.CurrencyDto;

@Service
public class CurrencyApiService {

	private RestTemplate restTemplate;
	private static final String ACCESS_KEY ="8711282f6324e9bbcaf2ea89273b8953";
	private static final String BASE_URL="http://api.currencylayer.com/";
	private static final String SOURCE ="USD";
	private static final String ENDPOINT ="live";

	private final static Logger logger = LoggerFactory.getLogger(CurrencyApiService.class);

	// 환율 정보가져오기 
	public double getCurrencyInfo(String currency) {
		restTemplate = new RestTemplate();
		double curr= 0.0;
		System.out.println("service진입");
		CurrencyDto c = restTemplate.getForObject(
				BASE_URL+ENDPOINT+"?access_key=" + ACCESS_KEY+"&currencies="+currency+"&source="+SOURCE
				, CurrencyDto.class);
		try {
			// true
			if( c.isSuccess()) {
				curr = c.getQuotes().get(SOURCE+currency);

				// 소수점 둘째 자리 까지
				curr = Math.round(curr * 100)/100.0;
				
			}
		}catch(Exception e) {
			
			e.printStackTrace();
		}

		return curr;
	}
	
	@ExceptionHandler(NullPointerException.class)
	public String handleNullPointerException(NullPointerException e) {
		return "error";
	}

}
