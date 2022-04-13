package com.etg.gateway.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Pattern;

import com.etg.gateway.common.Constants;
import com.etg.gateway.configurations.CustomLocalDateTimeDesSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CurrencyExchangeBaseDto {
	@Pattern(regexp = Constants.CURRENCY_REGEX, message = Constants.CURRENCY_NOT_SUPPORTED_MESSAGE)
	private String base;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private LocalDate date;

	@JsonDeserialize(using = CustomLocalDateTimeDesSerializer.class)
	private LocalDateTime timestamp;

}
