package com.etg.gateway.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CurrencyExchangeRequest {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "currency_exchange_request_id_seq")
	@SequenceGenerator(name = "currency_exchange_request_id_seq", sequenceName = "currency_exchange_request_id_seq")
	private Integer currencyExchangeRequestId;

	private Integer client;
	private String requestId;
	private LocalDateTime timestamp;
	private String currency;

	public CurrencyExchangeRequest(String requestId, Integer client, LocalDateTime dateTime, String currency) {
		this.requestId = requestId;
		this.client = client;
		this.timestamp = dateTime;
		;
		this.currency = currency;
	}

}
