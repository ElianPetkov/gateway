package com.etg.gateway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etg.gateway.common.CommonUtils;
import com.etg.gateway.dto.CurrencyExchangeResponseDto;
import com.etg.gateway.dto.FixerDataDto;
import com.etg.gateway.models.CurrencyExchangeRate;
import com.etg.gateway.models.ExchangeData;
import com.etg.gateway.repositories.ExchangeDataRepository;

@Service
@Transactional
public class ExchangeDataServiceImpl implements ExchangeDataService {

	@Autowired
	private ExchangeDataRepository exchangeDataRepository;

	@Override
	public CurrencyExchangeResponseDto saveExchangeData(FixerDataDto exchangeDataDto) {
		ExchangeData exchangeData = new ExchangeData(exchangeDataDto.getBase(), exchangeDataDto.getDate(),
				exchangeDataDto.getTimestamp());

		List<CurrencyExchangeRate> rates = convertMapRatesToList(exchangeDataDto.getRates(), exchangeData);
		exchangeData.setRates(rates);

		return CommonUtils.createExchangeResponseDto(exchangeDataRepository.saveAndFlush(exchangeData));

	}

	private List<CurrencyExchangeRate> convertMapRatesToList(Map<String, Double> mapRates, ExchangeData exchangeData) {
		List<CurrencyExchangeRate> rates = new ArrayList<>();

		if (mapRates != null) {
			mapRates.forEach((currency, currencyRate) -> {
				CurrencyExchangeRate exchangeRate = new CurrencyExchangeRate(currency, currencyRate, exchangeData);
				rates.add(exchangeRate);
			});
		}

		return rates;
	}

}
