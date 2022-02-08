package com.sera.exrate.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.sera.exrate.domain.CurrencyResponseDto;
import com.sera.exrate.service.CurrencyApiService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class ExrateControllerTest {
	
	@Mock
	CurrencyResponseDto response;
	
	@Mock
	CurrencyApiService service;
	
	@InjectMocks
	ExrateController controller;
	
	//@Autowired
	MockMvc mockMvc;
	
	@Before
	public void setUp() {
		System.out.println("before");
		//MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

	}
	
	@Test
	public void test() throws Exception {
		
		//when( controller.getExrate("KRW")).thenReturn(response);
		
		mockMvc.perform(get("/getExrate&currencies=KRW"))
		.andExpect(status().isOk());
	}


}
