package com.etg.gateway.dto;

import java.time.LocalDateTime;

import com.etg.gateway.configurations.CustomLocalDateTimeDesSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PeriodExchangeSearchJsonDto extends CurrencyExchangeSearchByPeriodDto {
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime timestamp;

	public PeriodExchangeSearchJsonDto(String requestId, int client, String currency, LocalDateTime timestamp,
			int period) {
		super(requestId, client, currency, period);
		this.timestamp = timestamp;
	}
}
