package com.cyganski.databaseManagement;

import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Service
public class CurrencyDeserializer {

    public List<CurrencyPair> convertCurrency(List<String> currencyData){

        List<CurrencyPair> convertedCurrencies = new ArrayList<>();

        for (int i =0;i<currencyData.stream().count();i++) {
            String[] shatteredString = currencyData.get(i).split(",");
            String format = "dd/MMM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
            LocalDate calculatedDate = LocalDate.parse(shatteredString[0],dateFormatter);
            double currencyExchange= Double.parseDouble(shatteredString[2]);
            convertedCurrencies.add(new CurrencyPair(checkAbbreviate(shatteredString[1]),"RON",calculatedDate,currencyExchange));
        }

        return convertedCurrencies;
        }
    private String checkAbbreviate(String fullName){
        switch(fullName){
            case "Canadian dollar"->{return "CAD";}
            case "Swiss franc"->{return "CHF";}
            case "Euro"->{return "EUR";}
            case "Pound sterling"-> {return "GBP";}
            case "Norwegian krone"-> {return "NOK";}
            case "Polish zloty"-> {return "PLN";}
            case "Swedish krona"->{return "SEK";}
            case "USD"->{return "USD";}
            case "Russian rouble"->{return "RUB";}
            case "Ukrainian hryvnia"->{return "UAH";}

        }
        return "Error";
    }

}
