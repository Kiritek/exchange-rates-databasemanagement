package com.cyganski.databaseManagement;

import com.cyganski.databaseManagement.CurrencyDeserializer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencySaver {
    List<String> collectedData = new ArrayList<>();
    CurrencyDeserializer currencyDeserializer;
    CurrencyCalculator currencyCalculator;

    public CurrencySaver(CurrencyDeserializer currencyDeserializer, CurrencyCalculator currencyCalculator) {
        this.currencyDeserializer = currencyDeserializer;
        this.currencyCalculator = currencyCalculator;
    }

    public void saveCurrencyToDb(List<String> data) {
        List<CurrencyPair> currencyPairs = currencyDeserializer.convertCurrency(data);
        List<CurrencyPair> allPairs = currencyCalculator.calculateCurrencies(currencyPairs);
        for (int i=0; i<allPairs.stream().count();i++){
            System.out.println(allPairs.get(i).toString());
        }
        allPairs.stream().count();
    }

    public void collectData(String data) {

        if (!data.equals("EndOfTransmission")) {
            collectedData.add(data);
        }else {
            saveCurrencyToDb(collectedData);
            collectedData.clear();
        }
    }
}