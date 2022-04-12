package com.etg.gateway.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.etg.gateway.common.CommonUtils;
import com.etg.gateway.common.Constants;
import com.etg.gateway.dto.ClientCurrencyExchangeBaseDto;
import com.etg.gateway.dto.CurrencyExchangeResponseDto;
import com.etg.gateway.dto.LatestClientCurrencyExchangeDto;
import com.etg.gateway.dto.PeriodClientCurrencyExchangeDto;
import com.etg.gateway.exceptions.GateWayExcpetion;
import com.etg.gateway.models.CurrencyExchangeRequest;
import com.etg.gateway.models.ExchangeData;
import com.etg.gateway.repositories.CurrencyExchangeRequestRepository;
import com.etg.gateway.repositories.ExchangeDataRepository;

@Service
@Transactional
public class ClientExchangeServiceImp implements ClientExchangeService {

	@Autowired
	private CurrencyExchangeRequestRepository currencyExchangeRequestRepository;
	@Autowired
	private ExchangeDataRepository exchangeDataRepository;

	@Override
	public CurrencyExchangeResponseDto extractLatestCurrencyExchangeInformation(
			LatestClientCurrencyExchangeDto latestCurrencyExchangeDto) {

		checkForDuplicatedRequestId(latestCurrencyExchangeDto.getRequestId());
		createAndSaveClientRequest(latestCurrencyExchangeDto);

		List<ExchangeData> exchangeData = findLatesExchangeData(latestCurrencyExchangeDto.getCurrency(), 1);

		if (exchangeData.size() == 0) {
			throw new GateWayExcpetion(Constants.NO_RESOURCE_FOUND_MESSAGE + latestCurrencyExchangeDto.getCurrency(),
					HttpStatus.NOT_FOUND);
		} else {
			return CommonUtils.createExchangeResponseDto(exchangeData.get(0));
		}
	}

	@Override
	public List<CurrencyExchangeResponseDto> extractCurrencyExchangesByPeriod(
			PeriodClientCurrencyExchangeDto periodCurrencyExchangeDto) {
		checkForDuplicatedRequestId(periodCurrencyExchangeDto.getRequestId());
		createAndSaveClientRequest(periodCurrencyExchangeDto);

		List<ExchangeData> exchangeData = new ArrayList<>();
		if (periodCurrencyExchangeDto.getTimestamp() != null) {
			exchangeData = findLatesExchangeData(periodCurrencyExchangeDto.getCurrency(),
					periodCurrencyExchangeDto.getTimestamp().minusHours(periodCurrencyExchangeDto.getPeriod()));
		}

		if (exchangeData.size() == 0) {
			throw new GateWayExcpetion(Constants.NO_RESOURCE_FOUND_MESSAGE + periodCurrencyExchangeDto.getCurrency(),
					HttpStatus.NOT_FOUND);
		} else {
			List<CurrencyExchangeResponseDto> result = new ArrayList<>();
			for (var data : exchangeData) {
				result.add(CommonUtils.createExchangeResponseDto(data));
			}
			return result;
		}
	}

	private List<ExchangeData> findLatesExchangeData(String currency, int pageSize) {
		return exchangeDataRepository.findFirstByOrderByTimeStampDesc(currency, PageRequest.of(0, pageSize));
	}

	private List<ExchangeData> findLatesExchangeData(String currency, LocalDateTime timestamp) {
		return exchangeDataRepository.findExchangeDataByPeriod(currency, timestamp);
	}

	private void checkForDuplicatedRequestId(String requestId) {
		currencyExchangeRequestRepository.findRequestByRequestId(requestId).ifPresent(duplicatedRequest -> {
			throw new GateWayExcpetion(Constants.DUPLICATED_REQUEST_MESSAGE, HttpStatus.CONFLICT);
		});
	}

	private void createAndSaveClientRequest(ClientCurrencyExchangeBaseDto data) {
		CurrencyExchangeRequest currencyExchangeRequest = new CurrencyExchangeRequest(data.getRequestId(),
				data.getClient(), data.getTimestamp(), Constants.JSON_API);
		currencyExchangeRequestRepository.saveAndFlush(currencyExchangeRequest);
	}

}
