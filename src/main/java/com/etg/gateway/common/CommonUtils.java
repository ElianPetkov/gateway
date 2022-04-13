package com.etg.gateway.common;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.etg.gateway.dto.CurrencyExchangeResponseDto;
import com.etg.gateway.dto.CurrencyRateDto;
import com.etg.gateway.models.ExchangeData;

public class CommonUtils {
	private CommonUtils() {

	};

	public static CurrencyExchangeResponseDto createExchangeResponseDto(ExchangeData exchangeData) {
		List<CurrencyRateDto> rates = new ArrayList<>();
		if (exchangeData.getRates() != null) {
			exchangeData.getRates().forEach(rate -> {
				CurrencyRateDto currencyExchange = new CurrencyRateDto(rate.getBase(), rate.getRate());
				rates.add(currencyExchange);
			});
		}

		return new CurrencyExchangeResponseDto(exchangeData.getBase(), exchangeData.getDate(),
				exchangeData.getDateTime(), rates);

	}

	public static LocalDateTime getCurrentTimeInUtc() {
		return LocalDateTime.now(ZoneOffset.UTC);
	}
}
