package com.etg.gateway.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PeriodClientCurrencyExchangeDto extends ClientCurrencyExchangeBaseDto {
	private int period;

	public PeriodClientCurrencyExchangeDto(String requestId, int client, String currency, LocalDateTime timestamp,
			int period) {
		super(client, currency, timestamp, requestId);
		this.period = period;
	}
}
