package com.etg.gateway.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class ExchangeData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "exchange_data_id_seq")
	@SequenceGenerator(name = "exchange_data_id_seq", sequenceName = "exchange_data_id_seq")
	private Integer exchangeDataId;

	private String base;
	private LocalDate date;
	private LocalDateTime dateTime;

	public ExchangeData(String base, LocalDate date, LocalDateTime dateTime) {
		this.base = base;
		this.date = date;
		this.dateTime = dateTime;
	}

	@OneToMany(mappedBy = "exchangeData", cascade = CascadeType.ALL)
	private List<CurrencyExchangeRate> rates;
}
