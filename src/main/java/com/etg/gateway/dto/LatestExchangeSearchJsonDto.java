package com.etg.gateway.dto;

import java.time.LocalDateTime;

import com.etg.gateway.configurations.CustomLocalDateTimeDesSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class LatestExchangeSearchJsonDto extends CurrencyExchangeSearchBaseDto {
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime timestamp;

	public LatestExchangeSearchJsonDto(Integer client, String currency, LocalDateTime timestamp, String requestId) {
		super(client, currency, requestId);
		this.timestamp = timestamp;
	}

}
