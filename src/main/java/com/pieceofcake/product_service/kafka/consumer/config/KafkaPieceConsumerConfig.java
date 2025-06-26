package com.pieceofcake.product_service.kafka.consumer.config;

import com.pieceofcake.product_service.kafka.consumer.event.PieceReadEvent;
import com.pieceofcake.product_service.kafka.consumer.event.ProductReadEvent;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaPieceConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * 공통 consumer config 설정
     */
    @Bean
    public Map<String, Object> pieceConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "product-create-piece-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, PieceReadEvent.class); // 필요시 ProductEvent로 수정

        return props;
    }

    /**
     * Kafka ConsumerFactory 설정
     */
    @Bean
    public ConsumerFactory<String, PieceReadEvent> pieceConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                pieceConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(PieceReadEvent.class, false))
        );
    }

    /**
     * KafkaListenerContainerFactory 설정
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PieceReadEvent> pieceEventListener() {
        ConcurrentKafkaListenerContainerFactory<String, PieceReadEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(pieceConsumerFactory());
        return factory;
    }

}
