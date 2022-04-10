package com.etg.gateway.entities;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ExchangeData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer exchangeDataId;

	private String base;
	private String date;
	private BigDecimal timeStamp;

	public ExchangeData(String base, String date, BigDecimal timeStamp) {
		this.base = base;
		this.date = date;
		this.timeStamp = timeStamp;
	}

	@OneToMany(mappedBy = "exchangeData", cascade = CascadeType.ALL)
	private List<ExchangeRate> rates;
}
