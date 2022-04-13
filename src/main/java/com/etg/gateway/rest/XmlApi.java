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
import com.etg.gateway.dto.PeriodExchangeSearchXmlDto;
import com.etg.gateway.dto.LatestExchangeSearchXmlDto;
import com.etg.gateway.service.ClientExchangeService;

@RestController
@RequestMapping("/xml_api")
public class XmlApi {
	@Autowired
	private ClientExchangeService clientExchangeService;

	@RequestMapping(value = "/history", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public List<CurrencyExchangeResponseDto> getCurrentExchangeDataByPeriod(
			@RequestBody @Valid PeriodExchangeSearchXmlDto period) {
		period.setServiceInfo(Constants.XML_API);
		return clientExchangeService.extractCurrencyExchangesByPeriod(period);
	}

	@RequestMapping(value = "/current", method = RequestMethod.POST, produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public CurrencyExchangeResponseDto getLatestCurrentExchangeData(
			@RequestBody @Valid LatestExchangeSearchXmlDto latest) {
		latest.setServiceInfo(Constants.XML_API);
		return clientExchangeService.extractLatestCurrencyExchangeInformation(latest);
	}

}
