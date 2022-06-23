package com.exchangeRates.databaseManagement.repositories;

import com.exchangeRates.databaseManagement.entities.CurrencyPair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyPairRepository extends JpaRepository<CurrencyPair,Long> {

}
