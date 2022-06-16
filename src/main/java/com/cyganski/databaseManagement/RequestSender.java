package com.cyganski.databaseManagement;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class RequestSender {
    private KafkaTemplate<String,String> kafkaTemplate;
    public RequestSender(KafkaTemplate<String,String> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    public void requestHistoricalData(){
        kafkaTemplate.send("exchangeRatesRequests","RequestPreviousRates");
    }
}
