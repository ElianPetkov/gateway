package com.etg.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FixerCurrencyRateDto {
	private String base;
	private Double rate;

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof FixerCurrencyRateDto)) {
			return false;
		}

		FixerCurrencyRateDto c = (FixerCurrencyRateDto) o;

		return base.equals(c.base) && Double.compare(rate, c.rate) == 0;
	}
}
