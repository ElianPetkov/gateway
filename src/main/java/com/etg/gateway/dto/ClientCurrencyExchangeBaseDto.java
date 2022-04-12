package com.etg.gateway.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.etg.gateway.common.Constants;
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
	@NotNull(message = Constants.CLIENT_ID_NOT_NULL_MESSAGE)
	private Integer client;

	@Pattern(regexp = Constants.CURRENCY_REGEX, message = Constants.CURRENCY_NOT_SUPPORTED_MESSAGE)
	private String currency;

	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime timestamp;

	@NotNull(message = Constants.REQUEST_ID_NOT_NULL_MESSAGE)
	private String requestId;

}
