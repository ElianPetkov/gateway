package com.etg.gateway.service;

import java.util.List;

import com.etg.gateway.dto.CurrencyExchangeResponseDto;
import com.etg.gateway.dto.LatestClientCurrencyExchangeDto;
import com.etg.gateway.dto.PeriodClientCurrencyExchangeDto;

public interface ClientExchangeService {
	public CurrencyExchangeResponseDto extractLatestCurrencyExchangeInformation(
			LatestClientCurrencyExchangeDto latestCurrencyExchangeDto);

	public List<CurrencyExchangeResponseDto> extractCurrencyExchangesByPeriod(
			PeriodClientCurrencyExchangeDto periodCurrencyExchangeDto);

}
