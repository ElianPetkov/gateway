package com.etg.gateway.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FixerDataDto extends CurrencyExchangeBaseDto {
	private Map<String, Double> rates;
	private boolean success;
	private FixerApiErrorDto error;

	public FixerDataDto(boolean success, FixerApiErrorDto error) {
		this.success = success;
		this.error = error;
	}

	public FixerDataDto(String base, LocalDate date, LocalDateTime timestamp, Map<String, Double> rates,
			boolean success, FixerApiErrorDto error) {
		super(base, date, timestamp);
		this.rates = rates;
		this.success = success;
		this.error = error;
	}
}
