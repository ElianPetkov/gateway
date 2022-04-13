package com.etg.gateway.dto;

import javax.validation.constraints.Pattern;

import com.etg.gateway.common.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyRateDto {
	@Pattern(regexp = Constants.CURRENCY_REGEX, message = Constants.CURRENCY_NOT_SUPPORTED_MESSAGE)
	private String base;

	private Double rate;

	@Override
	public boolean equals(Object o) {

		if (o == this) {
			return true;
		}

		if (!(o instanceof CurrencyRateDto)) {
			return false;
		}

		CurrencyRateDto c = (CurrencyRateDto) o;

		return base.equals(c.base) && Double.compare(rate, c.rate) == 0;
	}
}
