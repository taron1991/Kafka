package com.example.kafkademo.kafkaConfig;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;


//Конфигурация через Класс
@Configuration
public class KafkaConfiguration {

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonDeserializer");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put("spring.json.trusted.packages", "com.example.kafkademo.model");
        return props;
    }


    /**
     * Метод consumerFactory() в классе конфигурации для Kafka в Spring Boot используется для создания экземпляра
     * DefaultKafkaConsumerFactory.
     * Этот метод создает фабрику, которая будет использоваться для создания экземпляров потребителей Kafka.
     * @return
     */
    @Bean
    public DefaultKafkaConsumerFactory<String, Object> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }


    /**
     * Метод kafkaListenerContainerFactory() в классе конфигурации для Kafka в Spring Boot используется для создания экземпляра
     * ConcurrentKafkaListenerContainerFactory. Этот метод создает фабрику, которая будет
     * использоваться для создания контейнеров прослушивания сообщений Kafka.
     * @return
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.springframework.kafka.support.serializer.JsonSerializer");
        return props;
    }

    /**
     * В этом методе создается экземпляр DefaultKafkaProducerFactory, который используется
     * для настройки и создания производителя Kafka.
     * Мы передаем конфигурацию производителя, которую мы определили в методе producerConfigs(),
     * чтобы сконфигурировать DefaultKafkaProducerFactory.
     * @return
     */
    @Bean
    public DefaultKafkaProducerFactory<String, Object> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }


    /**
     * В этом методе создается экземпляр KafkaTemplate, который предоставляет удобный способ отправки сообщений в Kafka.
     * Мы передаем экземпляр DefaultKafkaProducerFactory в конструктор KafkaTemplate, чтобы связать его с нашим производителем Kafka.
     * @return
     */
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}
