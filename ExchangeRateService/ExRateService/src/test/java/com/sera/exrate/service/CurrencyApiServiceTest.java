package com.sera.exrate.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sera.exrate.domain.CurrencyResponseDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CurrencyApiServiceTest {


	@InjectMocks
	CurrencyApiService service;
	
	@Mock
	CurrencyResponseDto response;
	
	@Test
	public void test() throws Exception {
		if(service ==null) {
			System.out.println("service null");
		}
		if(response == null) {
			System.out.println("response null");
		}
		
		Mockito.when(service.getCurrencyInfo("KRW")).thenReturn(response);
		
		assertEquals(response.isSuccess(),true);
	}

}
