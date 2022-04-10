package com.etg.gateway.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.etg.gateway.dto.ExchangeDataDto;
import com.etg.gateway.dto.FixerApiErrorDto;
import com.etg.gateway.exceptions.FixerApiResponseException;
import com.etg.gateway.service.ExchangeDataService;

@Component
public class ScheduledCalls {
	@Value("${access_key}")
	private String access_key;

	@Value("${fixer}")
	private String fixer;

	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ExchangeDataService exchangeDataService;

	@Scheduled(cron = "${currencyDataReloadTime}")
	public void updateCurencyData() {
		ExchangeDataDto еxchangeValue = restTemplate.getForObject(fixer + access_key, ExchangeDataDto.class);

		if (еxchangeValue != null && !еxchangeValue.isSuccess()) {
			final FixerApiErrorDto fixerErrorDto = еxchangeValue.getError();
			throw new FixerApiResponseException(fixerErrorDto.getInfo(), HttpStatus.valueOf(fixerErrorDto.getCode()));
		}

		exchangeDataService.saveExchangeData(еxchangeValue);
	}
}
