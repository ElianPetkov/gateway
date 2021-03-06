package com.etg.gateway.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.etg.gateway.common.Constants;
import com.etg.gateway.dto.CurrencyExchangeResponseDto;
import com.etg.gateway.dto.LatestExchangeSearchJsonDto;
import com.etg.gateway.dto.PeriodExchangeSearchJsonDto;
import com.etg.gateway.service.ClientExchangeService;

@RestController
@RequestMapping("/json_api")
public class JsonApi {
	@Autowired
	private ClientExchangeService clientExchangeService;

	@RequestMapping(value = "/history", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<CurrencyExchangeResponseDto> getExchangeDataInPeriod(
			@RequestBody @Valid PeriodExchangeSearchJsonDto period) {
		period.setServiceInfo(Constants.JSON_API);
		return clientExchangeService.extractCurrencyExchangesByPeriod(period);
	}

	@RequestMapping(value = "/current", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public CurrencyExchangeResponseDto getCurrentExchangeData(@RequestBody @Valid LatestExchangeSearchJsonDto latest) {
		latest.setServiceInfo(Constants.JSON_API);
		return clientExchangeService.extractLatestCurrencyExchangeInformation(latest);
	}

}
