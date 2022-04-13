package com.etg.gateway.dto;

import java.time.LocalDateTime;

import com.etg.gateway.configurations.XmlDesSerializerForPeriodExchanges;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonDeserialize(using = XmlDesSerializerForPeriodExchanges.class)
public class PeriodExchangeSearchXmlDto extends CurrencyExchangeSearchByPeriodDto {

	public PeriodExchangeSearchXmlDto(String requestId, int client, String currency, LocalDateTime timestamp,
			int period) {
		super(requestId, client, currency, period);
	}

}
