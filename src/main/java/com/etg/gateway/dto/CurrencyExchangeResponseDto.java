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
public class CurrencyExchangeResponseDto extends FixerCurrencyExchangeBaseDto {
	private List<FixerCurrencyRateDto> rates;

	public CurrencyExchangeResponseDto(String base, LocalDate date, LocalDateTime timestamp,
			List<FixerCurrencyRateDto> rates) {
		super(base, date, timestamp);
		this.rates = rates;
	}

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof CurrencyExchangeResponseDto)) {
			return false;
		}

		CurrencyExchangeResponseDto c = (CurrencyExchangeResponseDto) o;

		// Compare the data members and return accordingly
		return rates.equals(c.rates) && super.getBase().equals(c.getBase()) && super.getDate().equals(c.getDate())
				&& super.getTimestamp().equals(c.getTimestamp());
	}
}
