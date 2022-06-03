package com.cyganski.databaseManagement;

import java.time.LocalDate;
import java.util.Date;

public class CurrencyPair {
    private LocalDate date;
    private String firstCurrency;
    private String secondCurrency;
    private double exchangeRate;

    public CurrencyPair(String firstCurrency, String secondCurrency, LocalDate date, double exchangeRate){
        this.firstCurrency = firstCurrency;
        this.secondCurrency = secondCurrency;
        this.exchangeRate = exchangeRate;
        this.date = date;
    }
    public String getFirstCurrency(){
        return firstCurrency;
    }
    public String getSecondCurrency(){
        return secondCurrency;
    }
    public LocalDate getDate(){
        return date;
    }
    public double getExchangeRate(){
        return exchangeRate;
    }



    @Override
    public String toString() {
        return date +","+firstCurrency+","+secondCurrency+","+exchangeRate;
    }




}
