package com.cyganski.databaseManagement.library;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CurrencyNameLibrary {
    private Map<String,String> currencyMap = new HashMap<>(){{
        put("Australian dollar", "AUD");
        put("Canadian dollar","CAD");
        put("Swiss franc","CHF");
        put("Czech koruna","CZK");
        put("Danish krone","DKK");
        put("Egyptian pound","EGP");
        put("Euro","EUR");
        put("Pound sterling","GBP");
        put("Hungarian forint","HUF");
        put("Japanese yen","JPY");
        put("Moldavian leu","MDL");
        put("Norwegian krone","NOK");
        put("Polish zloty","PLN");
        put("Swedish krona","SEK");
        put("Turkish lira","TRY");
        put("USD","USD");
        put("Gold","XAU");
        put("SDR","XDR");
        put("Russian rouble","RUB");
        put("Bulgarian lev", "BGN");
        put("South African rand","ZAR");
        put("Brazilian real","BRL");
        put("Chinese renminbi","CNY");
        put("Indian rupee","INR");
        put("South Korean wons","KRW");
        put("Mexican peso","MXN");
        put("New Zealand dollar","NZD");
        put("Serbian dinar","RSD");
        put("Ukrainian hryvnia","UAH");
        put("United Arab Emirates dirham","AED");
        put("Croatian kuna","HRK");
        put("Thai baht","THB");
        }};

    public String checkAbbreviate(String value) {
        return currencyMap.get(value);
    }
}
