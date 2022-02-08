package com.sera.exrate.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sera.exrate.domain.CurrencyResponseDto;
import com.sera.exrate.exception.CurrencyApiException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class CurrencyApiServiceTest {

	@InjectMocks
	CurrencyApiService service;

	@Mock
	CurrencyResponseDto response;
	
	private final static Logger logger = LoggerFactory.getLogger(CurrencyApiService.class);

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void test() throws Exception {
		logger.info("Api Service test");
		response = service.getCurrencyInfo("PHP");
		assertEquals(response.isSuccess(),true);
	}
	
	@Test
	public void exceptionTest() throws Exception {
		logger.info("service exceptoin test");
		when(service.getCurrencyInfo("")).thenThrow(new CurrencyApiException());
	}

}
