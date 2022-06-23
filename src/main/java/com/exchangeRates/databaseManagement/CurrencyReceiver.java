package com.exchangeRates.databaseManagement;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyReceiver {
    private CurrencySaver currencySaver;
    private List<String> collectedData = new ArrayList<>();
    public CurrencyReceiver(CurrencySaver currencySaver){
        this.currencySaver = currencySaver;
    }

    public void collectData(String data) {

        if (!data.equals("EndOfTransmission")) {
            collectedData.add(data);
        } else {
            currencySaver.saveCurrencyToDb(collectedData);
            collectedData.clear();
        }

    }

}
