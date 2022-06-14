package com.cyganski.databaseManagement.repositories;

import com.cyganski.databaseManagement.Enteties.CurrencyPair;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyPairRepository extends JpaRepository<CurrencyPair,Long> {


}
