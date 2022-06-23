package com.exchangeRates.databaseManagement.listeners;

import com.exchangeRates.databaseManagement.CurrencyReceiver;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    private CurrencyReceiver currencyReceiver;
    private int number = 0;
    KafkaListeners(CurrencyReceiver currencyReceiver){
        this.currencyReceiver = currencyReceiver;
    }

    @KafkaListener(topics = "exchangeRates",groupId = "exchangeRatesId")
    void listener(String data){
        currencyReceiver.collectData(data);
        }
    }
