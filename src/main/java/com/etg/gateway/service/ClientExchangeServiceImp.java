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
			return new CurrencyExchangeResponseDto();
		} else {
			return CommonUtils.createExchangeResponseDto(exchangeData.get(0));
		}
	}

	@Override
	public List<CurrencyExchangeResponseDto> extractCurrencyExchangesByPeriod(
			PeriodClientCurrencyExchangeDto periodCurrencyExchangeDto) {
		checkForDuplicatedRequestId(periodCurrencyExchangeDto.getRequestId());
		createAndSaveClientRequest(periodCurrencyExchangeDto);

		List<ExchangeData> exchangeData = findLatesExchangeData(periodCurrencyExchangeDto.getCurrency(),
				periodCurrencyExchangeDto.getTimestamp().minusHours(periodCurrencyExchangeDto.getPeriod()));

		if (exchangeData.size() == 0) {
			return new ArrayList<CurrencyExchangeResponseDto>();
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
			throw new GateWayExcpetion("This request has been already excecuted !", HttpStatus.CONFLICT);
		});
	}

	private void createAndSaveClientRequest(ClientCurrencyExchangeBaseDto data) {
		CurrencyExchangeRequest currencyExchangeRequest = new CurrencyExchangeRequest(data.getRequestId(),
				data.getClient(), data.getTimestamp(), data.getCurrency());
		currencyExchangeRequestRepository.saveAndFlush(currencyExchangeRequest);
	}

}
