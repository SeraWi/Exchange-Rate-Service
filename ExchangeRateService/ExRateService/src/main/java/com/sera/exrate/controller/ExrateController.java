package com.sera.exrate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sera.exrate.service.CurrencyApiService;

@RestController
public class ExrateController {
	
	@Autowired
	private CurrencyApiService currService;

	// 환율 가져오기
	@GetMapping("/getExrate")
	public double getExrate(@RequestParam("currency")String currency) {
		
		return currService.getCurrencyInfo(currency);
	}
	
	
	

	
}
