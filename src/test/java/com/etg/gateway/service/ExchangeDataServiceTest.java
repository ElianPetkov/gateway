package com.etg.gateway.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.etg.gateway.dto.CurrencyExchangeResponseDto;
import com.etg.gateway.dto.FixerCurrencyRateDto;
import com.etg.gateway.dto.FixerDataDto;
import com.etg.gateway.models.CurrencyExchangeRate;
import com.etg.gateway.models.ExchangeData;
import com.etg.gateway.repositories.ExchangeDataRepository;

@ExtendWith(MockitoExtension.class)
public class ExchangeDataServiceTest {
	@InjectMocks
	private ExchangeDataServiceImpl exchangeDataService;

	@Mock
	private ExchangeDataRepository exchangeDataRepository;

	private static LocalDate DATE = LocalDate.of(2022, 4, 12);
	private static LocalDateTime DATE_TIME = LocalDateTime.of(2022, 4, 12, 15, 30);
	private static String EUR = "Eur";
	private static List<FixerCurrencyRateDto> EXPECTED_RESPONSE_RATES = new ArrayList<>() {
		{
			add(new FixerCurrencyRateDto("BGN", 1.95));
			add(new FixerCurrencyRateDto("RO", 1.56));
		}
	};

	@Test
	public void testSaveFixerData() {
		ExchangeData exchangeData = new ExchangeData(EUR, DATE, DATE_TIME);
		List<CurrencyExchangeRate> rates = new ArrayList<>() {
			{
				add(new CurrencyExchangeRate("BGN", 1.95, exchangeData));
				add(new CurrencyExchangeRate("RO", 1.56, exchangeData));
			}
		};
		exchangeData.setRates(rates);
		when(exchangeDataRepository.saveAndFlush(ArgumentMatchers.<ExchangeData>any())).thenReturn(exchangeData);

		Map<String, Double> mapRates = new HashMap<>() {
			{
				put("BGN", 1.95);
				put("RO", 1.56);
			}
		};
		FixerDataDto fixerDataDto = new FixerDataDto(EUR, DATE, DATE_TIME, mapRates, true, null);

		CurrencyExchangeResponseDto response = exchangeDataService.saveExchangeData(fixerDataDto);
		List<FixerCurrencyRateDto> expectedResponseRates = new ArrayList<>() {
			{
				add(new FixerCurrencyRateDto("BGN", 1.95));
				add(new FixerCurrencyRateDto("RO", 1.56));
			}
		};
		Assertions.assertAll(() -> Assertions.assertEquals(response.getBase(), EUR),
				() -> Assertions.assertEquals(response.getTimestamp(), DATE_TIME),
				() -> Assertions.assertEquals(response.getDate(), DATE),
				() -> Assertions.assertTrue(response.getRates().equals(expectedResponseRates)));

	}

	@Test
	public void testSaveEmptyFixerData() {
		ExchangeData exchangeData = new ExchangeData();
		when(exchangeDataRepository.saveAndFlush(ArgumentMatchers.<ExchangeData>any())).thenReturn(exchangeData);

		FixerDataDto fixerDataDto = new FixerDataDto();

		CurrencyExchangeResponseDto response = exchangeDataService.saveExchangeData(fixerDataDto);

		Assertions.assertAll(() -> Assertions.assertEquals(response.getBase(), null),
				() -> Assertions.assertEquals(response.getTimestamp(), null),
				() -> Assertions.assertEquals(response.getDate(), null),
				() -> Assertions.assertTrue(response.getRates().equals(new ArrayList<FixerCurrencyRateDto>())));
	}
}
