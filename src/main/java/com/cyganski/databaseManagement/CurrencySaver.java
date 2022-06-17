package com.cyganski.databaseManagement;

import com.cyganski.databaseManagement.entities.CurrencyPair;
import com.cyganski.databaseManagement.repositories.CurrencyPairRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CurrencySaver {

    CurrencyDeserializer currencyDeserializer;
    CurrencyCalculator currencyCalculator;
    CurrencyPairRepository currencyPairRepository;
    RequestSender requestSender;

    public CurrencySaver(CurrencyDeserializer currencyDeserializer, CurrencyCalculator currencyCalculator, CurrencyPairRepository currencyPairRepository, RequestSender requestSender) {
        this.currencyDeserializer = currencyDeserializer;
        this.currencyCalculator = currencyCalculator;
        this.currencyPairRepository = currencyPairRepository;
        this.requestSender = requestSender;
    }

    public void saveCurrencyToDb(List<String> collectedData) {

        List<CurrencyPair> currencyPairs = currencyDeserializer.convertCurrency(collectedData);
        List<CurrencyPair> allPairs = currencyCalculator.calculateCurrencies(currencyPairs);
        currencyPairRepository.saveAll(allPairs);
        ;
        if(currencyPairRepository.findAll().size()==1056){
         requestSender.requestHistoricalData();
        }

    }


}