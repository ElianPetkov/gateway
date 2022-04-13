package com.etg.gateway.dto;

import javax.validation.constraints.Min;

import com.etg.gateway.common.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class CurrencyExchangeSearchByPeriodDto extends CurrencyExchangeSearchBaseDto {
	@Min(value = 0, message = Constants.PERIOD_POSITIVE_ERROR_MESSAGE)
	private int period;

	public CurrencyExchangeSearchByPeriodDto(String requestId, int client, String currency, int period) {
		super(client, currency, requestId);
		this.period = period;
	}
}
