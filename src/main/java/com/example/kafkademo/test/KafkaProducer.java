package com.example.kafkademo.test;

import com.example.kafkademo.model.Book;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Data
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class KafkaProducer {

    private static final String STRING_TOPIC = "string_topic";
    private static final String OBJECT_TOPIC = "object_topic";
    private static final String NUMBER_TOPIC = "number_topic";

    private final KafkaTemplate<String, Object> kafkaTemplate;


    public void sendMessage(String message) {
        kafkaTemplate.send(STRING_TOPIC, message);
    }

    public void sendMessage(Book object) {
        kafkaTemplate.send(OBJECT_TOPIC, object);
    }

    public void sendMessage(Integer number) {
        kafkaTemplate.send(NUMBER_TOPIC, number);
    }
}
