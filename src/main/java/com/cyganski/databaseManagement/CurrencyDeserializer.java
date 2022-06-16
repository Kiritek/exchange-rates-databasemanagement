package com.cyganski.databaseManagement;

import com.cyganski.databaseManagement.Enteties.CurrencyPair;
import com.cyganski.databaseManagement.library.CurrencyNameLibrary;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class CurrencyDeserializer {

    private final CurrencyNameLibrary currencyNameLibrary;
    public CurrencyDeserializer(CurrencyNameLibrary currencyNameLibrary){
        this.currencyNameLibrary= currencyNameLibrary;
    }
    public List<CurrencyPair> convertCurrency(List<String> currencyData){

        List<CurrencyPair> convertedCurrencies = new ArrayList<>();

        for (int i = 0; i< (long) currencyData.size(); i++) {
            String[] shatteredString = currencyData.get(i).split(",");
            String format = "dd/MMM/yyyy";
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format, Locale.ENGLISH);
            LocalDate calculatedDate = LocalDate.parse(shatteredString[0],dateFormatter);
            double currencyExchange= Double.parseDouble(shatteredString[2]);
            convertedCurrencies.add(new CurrencyPair(currencyNameLibrary.checkAbbreviate(shatteredString[1]),"RON",currencyExchange,calculatedDate));
        }

        return convertedCurrencies;
        }


}
