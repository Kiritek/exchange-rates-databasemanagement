package com.cyganski.databaseManagement.listeners;

import com.cyganski.databaseManagement.CurrencySaver;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    private CurrencySaver currencySaver;
    private int number = 0;
    KafkaListeners(CurrencySaver currencySaver){
        this.currencySaver = currencySaver;
    }

    @KafkaListener(topics = "exchangeRates",groupId = "exchangeRatesId")
    void listener(String data){

           currencySaver.collectData(data);
        }
    }
