package com.etg.gateway.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FixerResponseDto extends FixerDataBaseDto {
	private List<CurrencyExchangeDto> rates;

	public FixerResponseDto(String base, LocalDate date, LocalDateTime timestamp, List<CurrencyExchangeDto> rates) {
		super(base, date, timestamp);
		this.rates = rates;
	}

}
