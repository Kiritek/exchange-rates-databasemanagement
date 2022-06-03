package com.cyganski.databaseManagement;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaListeners {
    private CurrencySaver currencySaver;
    KafkaListeners(CurrencySaver currencySaver){
        this.currencySaver = currencySaver;
    }

    @KafkaListener(topics = "exchangeRates",groupId = "exchangeRatesId")
    void listener(String data){
            currencySaver.collectData(data);
        }
    }
