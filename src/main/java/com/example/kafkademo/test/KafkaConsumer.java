package com.example.kafkademo.test;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "string_topic", groupId = "group_id_string")
    public void receiveString(String message) {
        System.out.println("Received string message: " + message);
    }

    @KafkaListener(topics = "object_topic", groupId = "group_id_object")
    public void receiveObject(Object object) {
        System.out.println("Received object message: " + object.toString());
    }


    @KafkaListener(topics = "number_topic", groupId = "group_id_number")
    public void receiveNumber(Integer number) {

        System.out.println("Received number message: " + number);
    }

}
