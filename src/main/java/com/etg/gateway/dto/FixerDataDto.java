package com.etg.gateway.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FixerDataDto extends FixerCurrencyExchangeBaseDto {
	Map<String, Double> rates;
	boolean success;
	private FixerApiErrorDto error;

	public FixerDataDto(boolean success, FixerApiErrorDto error) {
		this.success = success;
		this.error = error;
	}

}
