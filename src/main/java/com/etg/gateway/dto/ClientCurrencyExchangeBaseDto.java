package com.etg.gateway.dto;

import java.time.LocalDateTime;

import com.etg.gateway.configurations.CustomLocalDateTimeDesSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class ClientCurrencyExchangeBaseDto {
	private Integer client;
	private String currency;
	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime timestamp;
	private String requestId;

}
