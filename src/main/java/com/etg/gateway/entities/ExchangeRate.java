package com.etg.gateway.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExchangeRate {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer rateId;

	private String base;
	private Double rate;

	public ExchangeRate(String base, Double rate) {
		this.base = base;
		this.rate = rate;
	}

	@ManyToOne
	@JoinColumn(name = "exchangeDataId")
	private ExchangeData exchangeData;

}
