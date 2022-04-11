package com.etg.gateway.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etg.gateway.models.ExchangeData;

public interface ExchangeDataRepository extends JpaRepository<ExchangeData, Integer> {

}
