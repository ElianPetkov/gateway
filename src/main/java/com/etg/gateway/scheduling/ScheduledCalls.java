package com.etg.gateway.scheduling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.etg.gateway.dto.ExchangeValueDto;
import com.etg.gateway.dto.FixerApiErrorDto;
import com.etg.gateway.exceptions.FixerApiResponseException;

@Component
public class ScheduledCalls {
	// private static Logger logger = Logger.ge(Fixer.class);

	@Value("${access_key}")
	private String access_key;

	@Value("${fixer}")
	private String fixer;

	@Autowired
	private RestTemplate restTemplate;

	@Scheduled(cron = "${currencyDataReloadTime}")
	public void updateCurencyData() {
		ExchangeValueDto еxchangeValue = null;
		try {
			еxchangeValue = restTemplate.getForObject(fixer + access_key, ExchangeValueDto.class);
		} finally {
			if (еxchangeValue != null && !еxchangeValue.isSuccess()) {
				final FixerApiErrorDto fixerErrorDto = еxchangeValue.getError();
				throw new FixerApiResponseException(fixerErrorDto.getInfo(),
						HttpStatus.valueOf(fixerErrorDto.getCode()));
			}
		}
	}
}
