package com.etg.gateway.service;

import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import com.etg.gateway.dto.CurrencyExchangeResponseDto;
import com.etg.gateway.dto.FixerCurrencyRateDto;
import com.etg.gateway.dto.LatestClientCurrencyExchangeDto;
import com.etg.gateway.dto.PeriodClientCurrencyExchangeDto;
import com.etg.gateway.exceptions.GateWayExcpetion;
import com.etg.gateway.models.CurrencyExchangeRate;
import com.etg.gateway.models.ExchangeData;
import com.etg.gateway.repositories.CurrencyExchangeRequestRepository;
import com.etg.gateway.repositories.ExchangeDataRepository;

@ExtendWith(MockitoExtension.class)
public class ClientExchangeServiceImplTest {

	@Mock
	private CurrencyExchangeRequestRepository currencyExchangeRequestRepository;
	@Mock
	private ExchangeDataRepository exchangeDataRepository;
	@InjectMocks
	private ClientExchangeServiceImp clientExchangeServiceImpl;

	private static Integer CLIENT = Integer.valueOf(1234);
	private static LocalDateTime DATE_TIME = LocalDateTime.of(2022, 5, 11, 10, 20);
	private static String EUR = "Eur";
	private static String REQUEST_ID = "b89577fe-8c37-4962-8af3-7cb89a245160";
	private static LatestClientCurrencyExchangeDto LATEST_CURRENCY_DATA_DTO = new LatestClientCurrencyExchangeDto(
			CLIENT, EUR, DATE_TIME, REQUEST_ID);
	private static Integer PERIOD = Integer.valueOf(20);
	private static PeriodClientCurrencyExchangeDto PERIOD_CURRENCY_DATA_DTO = new PeriodClientCurrencyExchangeDto(
			REQUEST_ID, CLIENT, EUR, DATE_TIME, PERIOD);

	private static LocalDate DATE = LocalDate.of(2022, 4, 12);

	// exception tests
	@Test
	public void duplicatedRequestInExtractLatestCurrencyExchangeInformation() {
		when(currencyExchangeRequestRepository.findRequestByRequestId(ArgumentMatchers.<String>any()))
				.thenThrow(new GateWayExcpetion("This request has been already excecuted !", HttpStatus.CONFLICT));

		Assertions.assertThrows(GateWayExcpetion.class,
				() -> clientExchangeServiceImpl.extractLatestCurrencyExchangeInformation(LATEST_CURRENCY_DATA_DTO));
	}

	@Test
	public void duplicatedRequestInExtractCurrencyExchangesByPeriod() {
		when(currencyExchangeRequestRepository.findRequestByRequestId(ArgumentMatchers.<String>any()))
				.thenThrow(new GateWayExcpetion("This request has been already excecuted !", HttpStatus.CONFLICT));

		Assertions.assertThrows(GateWayExcpetion.class,
				() -> clientExchangeServiceImpl.extractCurrencyExchangesByPeriod(PERIOD_CURRENCY_DATA_DTO));
	}

	// findFirstByOrderByTimeStampDesc
	@Test
	public void findLatestExchangeDataTest() {
		ExchangeData repositoryExchangeDataResponse = new ExchangeData(EUR, DATE, DATE_TIME);
		List<CurrencyExchangeRate> rates = new ArrayList<>() {
			{
				add(new CurrencyExchangeRate("UK", 2.01, repositoryExchangeDataResponse));
			}
		};
		repositoryExchangeDataResponse.setRates(rates);
		List<ExchangeData> mockedRepositoryResponse = new ArrayList<>() {
			{
				add(repositoryExchangeDataResponse);
			}
		};
		when(exchangeDataRepository.findFirstByOrderByTimeStampDesc(ArgumentMatchers.<String>any(),
				ArgumentMatchers.<PageRequest>any())).thenReturn(mockedRepositoryResponse);

		CurrencyExchangeResponseDto response = clientExchangeServiceImpl
				.extractLatestCurrencyExchangeInformation(LATEST_CURRENCY_DATA_DTO);

		List<FixerCurrencyRateDto> expectedResponseRates = new ArrayList<>() {
			{
				add(new FixerCurrencyRateDto("UK", 2.01));
			}
		};
		CurrencyExchangeResponseDto expectedResponse = new CurrencyExchangeResponseDto(EUR, DATE, DATE_TIME,
				expectedResponseRates);

		Assertions.assertAll(() -> Assertions.assertEquals(response.getBase(), expectedResponse.getBase()),
				() -> Assertions.assertEquals(response.getTimestamp(), expectedResponse.getTimestamp()),
				() -> Assertions.assertEquals(response.getDate(), expectedResponse.getDate()),
				() -> Assertions.assertTrue(response.getRates().equals(expectedResponse.getRates())));
	}

	@Test
	public void findLatestExchangeDataWithEmptyResultTest() {

		List<ExchangeData> mockedRepositoryResponse = new ArrayList<>();
		when(exchangeDataRepository.findFirstByOrderByTimeStampDesc(ArgumentMatchers.<String>any(),
				ArgumentMatchers.<PageRequest>any())).thenReturn(mockedRepositoryResponse);

		Assertions.assertThrows(GateWayExcpetion.class,
				() -> clientExchangeServiceImpl.extractLatestCurrencyExchangeInformation(LATEST_CURRENCY_DATA_DTO));
	}

	@Test
	public void findLatestExchangeDataTestWithEmptyDto() {
		ExchangeData repositoryExchangeDataResponse = new ExchangeData();
		List<ExchangeData> mockedRepositoryResponse = new ArrayList<>() {
			{
				add(repositoryExchangeDataResponse);
			}
		};
		when(exchangeDataRepository.findFirstByOrderByTimeStampDesc(ArgumentMatchers.<String>any(),
				ArgumentMatchers.<PageRequest>any())).thenReturn(mockedRepositoryResponse);

		CurrencyExchangeResponseDto response = clientExchangeServiceImpl
				.extractLatestCurrencyExchangeInformation(new LatestClientCurrencyExchangeDto());

		CurrencyExchangeResponseDto expectedResponse = new CurrencyExchangeResponseDto();
		expectedResponse.setRates(new ArrayList<FixerCurrencyRateDto>());

		Assertions.assertAll(() -> Assertions.assertEquals(response.getBase(), expectedResponse.getBase()),
				() -> Assertions.assertEquals(response.getTimestamp(), expectedResponse.getTimestamp()),
				() -> Assertions.assertEquals(response.getDate(), expectedResponse.getDate()),
				() -> Assertions.assertTrue(response.getRates().equals(expectedResponse.getRates())));
	}

	// findExchangeDataByPeriod
	@Test
	public void findLatestExchangeDataByPeriodTest() {
		ExchangeData firstRepositoryExchangeDataResponse = new ExchangeData(EUR, DATE, DATE_TIME);
		List<CurrencyExchangeRate> firstRates = new ArrayList<>() {
			{
				add(new CurrencyExchangeRate("UK", 2.01, firstRepositoryExchangeDataResponse));
				add(new CurrencyExchangeRate("EG", 1.37, firstRepositoryExchangeDataResponse));
			}
		};
		firstRepositoryExchangeDataResponse.setRates(firstRates);

		ExchangeData secondRepositoryExchangeDataResponse = new ExchangeData("BGN", LocalDate.of(2021, 5, 27),
				LocalDateTime.of(2021, 5, 27, 5, 56));
		List<CurrencyExchangeRate> secondRates = new ArrayList<>() {
			{
				add(new CurrencyExchangeRate("TUR", 0.15, firstRepositoryExchangeDataResponse));
				add(new CurrencyExchangeRate("WE", 2.79, firstRepositoryExchangeDataResponse));
			}
		};
		secondRepositoryExchangeDataResponse.setRates(secondRates);
		List<ExchangeData> mockedRepositoryResponse = new ArrayList<>() {
			{
				add(firstRepositoryExchangeDataResponse);
				add(secondRepositoryExchangeDataResponse);
			}
		};
		when(exchangeDataRepository.findExchangeDataByPeriod(ArgumentMatchers.<String>any(),
				ArgumentMatchers.<LocalDateTime>any())).thenReturn(mockedRepositoryResponse);

		List<CurrencyExchangeResponseDto> response = clientExchangeServiceImpl
				.extractCurrencyExchangesByPeriod(PERIOD_CURRENCY_DATA_DTO);

		List<FixerCurrencyRateDto> firstExpectedRates = new ArrayList<>() {
			{
				add(new FixerCurrencyRateDto("UK", 2.01));
				add(new FixerCurrencyRateDto("EG", 1.37));
			}
		};
		List<FixerCurrencyRateDto> secondExpectedRates = new ArrayList<>() {
			{
				add(new FixerCurrencyRateDto("TUR", 0.15));
				add(new FixerCurrencyRateDto("WE", 2.79));
			}
		};

		List<CurrencyExchangeResponseDto> expectedResponse = new ArrayList<>() {
			{
				add(new CurrencyExchangeResponseDto(EUR, DATE, DATE_TIME, firstExpectedRates));
				add(new CurrencyExchangeResponseDto("BGN", LocalDate.of(2021, 5, 27),
						LocalDateTime.of(2021, 5, 27, 5, 56), secondExpectedRates));
			}
		};

		Assertions.assertAll(() -> Assertions.assertTrue(response.equals(expectedResponse)));
	}

	@Test
	public void findLatestExchangeDataByPeriodWithEmptyDtoTest() {
		Assertions.assertThrows(GateWayExcpetion.class, () -> clientExchangeServiceImpl
				.extractCurrencyExchangesByPeriod(new PeriodClientCurrencyExchangeDto()));

	}

}
