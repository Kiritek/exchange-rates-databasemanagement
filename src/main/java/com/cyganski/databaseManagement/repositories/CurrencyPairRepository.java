package com.cyganski.databaseManagement.repositories;

import com.cyganski.databaseManagement.Enteties.CurrencyPair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CurrencyPairRepository extends JpaRepository<CurrencyPair,Long> {

}
