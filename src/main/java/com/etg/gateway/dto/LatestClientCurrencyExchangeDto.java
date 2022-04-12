package com.etg.gateway.dto;

import java.time.LocalDateTime;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LatestClientCurrencyExchangeDto extends ClientCurrencyExchangeBaseDto {
	public LatestClientCurrencyExchangeDto(Integer client, String currency, LocalDateTime timestamp, String requestId) {
		super(client, currency, timestamp, requestId);
	}

}
