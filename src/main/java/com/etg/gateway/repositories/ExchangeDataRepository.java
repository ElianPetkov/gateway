package com.etg.gateway.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etg.gateway.models.ExchangeData;

@Repository
public interface ExchangeDataRepository extends JpaRepository<ExchangeData, Integer> {

	@Query("Select exchangeData From ExchangeData exchangeData WHERE exchangeData.base = :currency"
			+ " Order by exchangeData.dateTime desc")
	List<ExchangeData> findFirstByOrderByTimeStampDesc(String currency, Pageable pagaeble);

	@Query("Select exchangeData From ExchangeData exchangeData WHERE exchangeData.base = :currency AND "
			+ " exchangeData.dateTime >= :timestamp" + " Order by exchangeData.dateTime desc")
	List<ExchangeData> findExchangeDataByPeriod(String currency, LocalDateTime timestamp);

}
