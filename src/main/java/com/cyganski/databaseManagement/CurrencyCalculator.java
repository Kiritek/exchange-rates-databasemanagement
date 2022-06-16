package com.cyganski.databaseManagement;

import com.cyganski.databaseManagement.Enteties.CurrencyPair;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyCalculator {

    public List<CurrencyPair> calculateCurrencies(List<CurrencyPair> currenciesToCalculate) {
        List<CurrencyPair> halfCalculatedList = CalculateOtherCurrencies(currenciesToCalculate);
        return AddBaseCurrencies(halfCalculatedList,currenciesToCalculate);
    }

    private List<CurrencyPair> CalculateOtherCurrencies(List<CurrencyPair> currenciesToCalculate){
        List<CurrencyPair> calculatedList = new ArrayList<>();

        double currentCurrencyRate;
        for (int i = 0; i < (long) currenciesToCalculate.size(); i++) {
            for (int j = 0; j < (long) currenciesToCalculate.size(); j++) {
                if (i != j) {
                    currentCurrencyRate = ((1 / currenciesToCalculate.get(j).getExchangeRate())/(1 / currenciesToCalculate.get(i).getExchangeRate()));
                    calculatedList.add(
                            new CurrencyPair(
                                    currenciesToCalculate.get(i).getFirstCurrency(),
                                    currenciesToCalculate.get(j).getFirstCurrency(),
                                    currentCurrencyRate,
                                    currenciesToCalculate.get(j).getDate()
                                    ));
                }
            }
        }
        return calculatedList;
    }
    private List<CurrencyPair> AddBaseCurrencies(List<CurrencyPair> calculatedList, List<CurrencyPair> baseList){

        for (int i = 0; i< (long) baseList.size(); i++){
            calculatedList.add(baseList.get(i));
            calculatedList.add(
                    new CurrencyPair(
                    baseList.get(i).getSecondCurrency(),
                    baseList.get(i).getFirstCurrency(),
                            (double)1/baseList.get(i).getExchangeRate(),
                    baseList.get(i).getDate()
            ));
        }
        return calculatedList;
    }
}
