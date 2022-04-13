package com.etg.gateway.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.etg.gateway.common.Constants;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CurrencyExchangeSearchBaseDto {
	@NotNull(message = Constants.CLIENT_ID_NOT_NULL_MESSAGE)
	private Integer client;

	@Pattern(regexp = Constants.CURRENCY_REGEX, message = Constants.CURRENCY_NOT_SUPPORTED_MESSAGE)
	private String currency;

	@NotNull(message = Constants.REQUEST_ID_NOT_NULL_MESSAGE)
	private String requestId;

	@JsonIgnore
	private String serviceInfo;

	public CurrencyExchangeSearchBaseDto(Integer client, String currency, String requestId) {
		this.client = client;
		this.currency = currency;
		this.requestId = requestId;
	}

}
