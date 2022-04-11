package com.etg.gateway.service;

import com.etg.gateway.dto.FixerDataDto;
import com.etg.gateway.dto.FixerResponseDto;

public interface ExchangeDataService {

	public FixerResponseDto saveExchangeData(FixerDataDto exchangeData);
}
