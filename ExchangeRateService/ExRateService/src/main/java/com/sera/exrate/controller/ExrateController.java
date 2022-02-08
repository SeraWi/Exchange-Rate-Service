package com.sera.exrate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sera.exrate.domain.CurrencyResponseDto;
import com.sera.exrate.service.CurrencyApiService;

@RestController
public class ExrateController {
	
	@Autowired
	private CurrencyApiService currService;

	// 환율 정보 요청
	@GetMapping("/getExrate")
	public CurrencyResponseDto getExrate(@RequestParam("currency")String currency) throws Exception {
		
		return currService.getCurrencyInfo(currency);
	}
	
}
