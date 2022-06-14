package com.cyganski.databaseManagement;

import com.cyganski.databaseManagement.Enteties.CurrencyPair;
import com.cyganski.databaseManagement.repositories.CurrencyPairRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CurrencySaver {
    List<String> collectedData = new ArrayList<>();
    CurrencyDeserializer currencyDeserializer;
    CurrencyCalculator currencyCalculator;
    CurrencyPairRepository currencyPairRepository;

    public CurrencySaver(CurrencyDeserializer currencyDeserializer, CurrencyCalculator currencyCalculator, CurrencyPairRepository currencyPairRepository) {
        this.currencyDeserializer = currencyDeserializer;
        this.currencyCalculator = currencyCalculator;
        this.currencyPairRepository = currencyPairRepository;
    }

    public void saveCurrencyToDb() {

        List<CurrencyPair> currencyPairs = currencyDeserializer.convertCurrency(collectedData);
        List<CurrencyPair> allPairs = currencyCalculator.calculateCurrencies(currencyPairs);
        currencyPairRepository.saveAll(allPairs);

    }

    public void collectData(String data) {

        if (!data.equals("EndOfTransmission")) {
            collectedData.add(data);
        } else {
            saveCurrencyToDb();
            collectedData.clear();
        }

    }
}