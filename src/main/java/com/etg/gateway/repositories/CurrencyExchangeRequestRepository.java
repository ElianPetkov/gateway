package com.etg.gateway.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.etg.gateway.models.CurrencyExchangeRequest;

@Repository
public interface CurrencyExchangeRequestRepository extends JpaRepository<CurrencyExchangeRequest, Integer> {

	@Query("Select request From CurrencyExchangeRequest request WHERE request.requestId = :requestId")
	Optional<CurrencyExchangeRequest> findRequestByRequestId(String requestId);

}
