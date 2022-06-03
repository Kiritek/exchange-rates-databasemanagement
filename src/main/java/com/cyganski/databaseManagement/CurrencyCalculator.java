package com.cyganski.databaseManagement;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyCalculator {

    public List<CurrencyPair> calculateCurrencies(List<CurrencyPair> currenciesToCalculate) {
        List<CurrencyPair> halfCalculatedList = CalculateOtherCurrencies(currenciesToCalculate);
        List<CurrencyPair> finalCurrencyList = AddBaseCurrencies(halfCalculatedList,currenciesToCalculate);
        return finalCurrencyList;
    }
    private List<CurrencyPair> CalculateOtherCurrencies(List<CurrencyPair> currenciesToCalculate){
        List<CurrencyPair> calculatedList = new ArrayList<CurrencyPair>();
        double currentCurrencyRate;
        for (int i = 0; i < (long) currenciesToCalculate.size(); i++) {

            for (int j = 0; j < (long) currenciesToCalculate.size(); j++) {
                if (i != j) {
                    currentCurrencyRate = ((1 / currenciesToCalculate.get(j).getExchangeRate())/(1 / currenciesToCalculate.get(i).getExchangeRate()));
                    calculatedList.add(
                            new CurrencyPair(
                                    currenciesToCalculate.get(i).getFirstCurrency(),
                                    currenciesToCalculate.get(j).getFirstCurrency(),
                                    currenciesToCalculate.get(j).getDate(),
                                    currentCurrencyRate));
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
                    baseList.get(i).getDate(),
                            (double)1/baseList.get(i).getExchangeRate()));
        }
        return calculatedList;
    }
}
