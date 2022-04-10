package com.etg.gateway.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.etg.gateway.dto.ExchangeDataDto;
import com.etg.gateway.entities.ExchangeData;
import com.etg.gateway.entities.ExchangeRate;
import com.etg.gateway.repositories.ExchangeDataRepository;

@Service
public class ExchangeDataServiceImpl implements ExchangeDataService {

	@Autowired
	private ExchangeDataRepository exchangeDataRepository;

	@Override
	public void saveExchangeData(ExchangeDataDto exchangeDataDto) {
		ExchangeData exchangeData = new ExchangeData(exchangeDataDto.getBase(), exchangeDataDto.getDate(),
				exchangeDataDto.getTimeStamp());

		List<ExchangeRate> rates = convertMapRatesToList(exchangeDataDto.getRates());
		exchangeData.setRates(rates);

		exchangeDataRepository.saveAndFlush(exchangeData);

	}

	private List<ExchangeRate> convertMapRatesToList(Map<String, Double> mapRates) {
		List<ExchangeRate> rates = new ArrayList<ExchangeRate>();

		mapRates.forEach((key, val) -> {
			ExchangeRate rate = new ExchangeRate(key, val);
			rates.add(rate);
		});

		return rates;
	}

}
