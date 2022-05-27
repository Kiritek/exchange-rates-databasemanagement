package com.cyganski.databaseManagement;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "exchangeRates",groupId = "exchangeRatesId")
    void listener(String data){
        System.out.println("Listener recieved: " + data);
    }
}
