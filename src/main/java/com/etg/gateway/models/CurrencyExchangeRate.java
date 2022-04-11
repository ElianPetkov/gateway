package com.etg.gateway.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CurrencyExchangeRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "currency_exchange_rate_id_seq")
	@SequenceGenerator(name = "currency_exchange_rate_id_seq", sequenceName = "currency_exchange_rate_id_seq")
	private Integer rateId;

	private String base;
	private Double rate;

	public CurrencyExchangeRate(String base, Double rate, ExchangeData exchangeData) {
		this.base = base;
		this.rate = rate;
		this.exchangeData = exchangeData;
	}

	@ManyToOne
	@JoinColumn(name = "exchangeDataId", nullable = false)
	private ExchangeData exchangeData;

}
