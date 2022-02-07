package com.sera.exrate.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.sera.exrate.domain.CurrencyDto;
import com.sera.exrate.domain.CurrencyResponseDto;
import com.sera.exrate.exception.CurrencyApiException;

@Service
public class CurrencyApiService {

	private RestTemplate restTemplate;
	private static final String ACCESS_KEY ="08281ae9af13285af8e1910b61a75623";
	private static final String BASE_URL="http://api.currencylayer.com/";
	private static final String SOURCE ="USD";
	private static final String ENDPOINT ="live";

	private final static Logger logger = LoggerFactory.getLogger(CurrencyApiService.class);
	
	
	// 환율 정보 가져오기 
	public CurrencyResponseDto getCurrencyInfo(String currency) throws Exception {
		restTemplate = new RestTemplate();
		logger.info("currencyApi service ");
		
		// 환율 정보
		CurrencyDto c = restTemplate.getForObject(
				BASE_URL+ENDPOINT+"?access_key=" + ACCESS_KEY+"&currencies="+currency+"&source="+SOURCE
				, CurrencyDto.class);
		// 응답 Dto
		CurrencyResponseDto  res;
		
		// true
		if(c.isSuccess()) {
			double curr = c.getQuotes().get(SOURCE+currency);
			// 소수점 둘째 자리 까지 표현
			curr = Math.ceil(curr * 100)/100.0;
			
			// 응답Dto 세팅
			res = new CurrencyResponseDto();
			res.setCurrency(curr);
			res.setSuccess(true);
			res.setMsg("success");
			
		}else {
			// 환율 가져오지 못함 success:false --> exception 발생
			logger.info("CurrencyApiService에서 Exception발생");
			throw new CurrencyApiException();
		}

		return res;
	}

}
