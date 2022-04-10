package com.etg.gateway.dto;

import java.math.BigDecimal;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeValueDto {
	private boolean success;
	private String base;
	private String date;
	private BigDecimal timeStamp;
	private Map<String, Double> rates;
	private FixerApiErrorDto error;

	public ExchangeValueDto(boolean success, FixerApiErrorDto error) {
		this.success = success;
		this.error = error;
	}

}
