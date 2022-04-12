package com.etg.gateway.common;

import java.util.ArrayList;
import java.util.List;

import com.etg.gateway.dto.CurrencyExchangeResponseDto;
import com.etg.gateway.dto.FixerCurrencyRateDto;
import com.etg.gateway.models.ExchangeData;

public class CommonUtils {
	private CommonUtils() {

	};

	public static CurrencyExchangeResponseDto createExchangeResponseDto(ExchangeData exchangeData) {
		List<FixerCurrencyRateDto> rates = new ArrayList<>();
		if (exchangeData.getRates() != null) {
			exchangeData.getRates().forEach(rate -> {
				FixerCurrencyRateDto currencyExchange = new FixerCurrencyRateDto(rate.getBase(), rate.getRate());
				rates.add(currencyExchange);
			});
		}

		return new CurrencyExchangeResponseDto(exchangeData.getBase(), exchangeData.getDate(),
				exchangeData.getDateTime(), rates);

	}
}
