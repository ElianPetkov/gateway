package com.etg.gateway.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;

import com.etg.gateway.common.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class PeriodClientCurrencyExchangeDto extends ClientCurrencyExchangeBaseDto {
	@Min(value = 0, message = Constants.PERIOD_POSITIVE_ERROR_MESSAGE)
	private int period;

	public PeriodClientCurrencyExchangeDto(String requestId, int client, String currency, LocalDateTime timestamp,
			int period) {
		super(client, currency, timestamp, requestId);
		this.period = period;
	}
}
