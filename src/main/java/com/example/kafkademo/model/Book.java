package com.example.kafkademo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    private int id;

    private String name;

    private String author;

    private int pages;
}
