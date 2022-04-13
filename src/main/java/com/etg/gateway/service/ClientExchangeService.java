package com.etg.gateway.service;

import java.util.List;

import com.etg.gateway.dto.CurrencyExchangeSearchBaseDto;
import com.etg.gateway.dto.CurrencyExchangeSearchByPeriodDto;
import com.etg.gateway.dto.CurrencyExchangeResponseDto;

public interface ClientExchangeService {
	public CurrencyExchangeResponseDto extractLatestCurrencyExchangeInformation(
			CurrencyExchangeSearchBaseDto latestCurrencyExchangeDto);

	public List<CurrencyExchangeResponseDto> extractCurrencyExchangesByPeriod(
			CurrencyExchangeSearchByPeriodDto periodCurrencyExchangeDto);

}
