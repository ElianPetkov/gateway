package com.etg.gateway.service;

import com.etg.gateway.dto.FixerDataDto;
import com.etg.gateway.dto.CurrencyExchangeResponseDto;

public interface ExchangeDataService {

	public CurrencyExchangeResponseDto saveExchangeData(FixerDataDto exchangeData);
}
