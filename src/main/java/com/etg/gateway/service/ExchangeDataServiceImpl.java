package com.etg.gateway.service;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etg.gateway.dto.CurrencyExchangeDto;
import com.etg.gateway.dto.FixerDataDto;
import com.etg.gateway.dto.FixerResponseDto;
import com.etg.gateway.models.CurrencyExchangeRate;
import com.etg.gateway.models.ExchangeData;
import com.etg.gateway.repositories.ExchangeDataRepository;

@Service
@Transactional
public class ExchangeDataServiceImpl implements ExchangeDataService {

	@Autowired
	private ExchangeDataRepository exchangeDataRepository;

	@Override
	public FixerResponseDto saveExchangeData(FixerDataDto exchangeDataDto) {
		ExchangeData exchangeData = new ExchangeData(exchangeDataDto.getBase(), exchangeDataDto.getDate(),
				exchangeDataDto.getTimestamp());

		List<CurrencyExchangeRate> rates = convertMapRatesToList(exchangeDataDto.getRates(), exchangeData);
		exchangeData.setRates(rates);

		return createExchangeResponseDto(exchangeDataRepository.saveAndFlush(exchangeData));

	}

	private List<CurrencyExchangeRate> convertMapRatesToList(Map<String, Double> mapRates, ExchangeData exchangeData) {
		List<CurrencyExchangeRate> rates = new ArrayList<>();

		mapRates.forEach((currency, currencyRate) -> {
			CurrencyExchangeRate exchangeRate = new CurrencyExchangeRate(currency, currencyRate, exchangeData);
			rates.add(exchangeRate);
		});

		return rates;
	}

	private FixerResponseDto createExchangeResponseDto(ExchangeData exchangeData) {
		List<CurrencyExchangeDto> rates = new ArrayList<>();
		exchangeData.getRates().forEach(rate -> {
			CurrencyExchangeDto currencyExchange = new CurrencyExchangeDto(rate.getBase(), rate.getRate());
			rates.add(currencyExchange);
		});

		return new FixerResponseDto(exchangeData.getBase(), exchangeData.getDate(),
				exchangeData.getTimeStamp().toInstant().atZone(ZoneOffset.systemDefault()).toLocalDateTime(), rates);

	}

}
