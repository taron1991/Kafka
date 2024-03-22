package com.example.kafkademo.test;



import com.example.kafkademo.model.Book;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kafka")
public class MainController {

    private KafkaProducer kafkaProducer;


    @Autowired
    public MainController(KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @PostMapping("/sendString")
    public void sendStringMessage(@RequestBody String message) {
        kafkaProducer.sendMessage(message);
    }

    @PostMapping("/sendObject")
    public void sendObjectMessage(@RequestBody Book object) {
        kafkaProducer.sendMessage(object);
    }

    @PostMapping("/sendNumber")
    public void sendNumberMessage(@RequestBody Integer number) {
        kafkaProducer.sendMessage(number);
    }
}
