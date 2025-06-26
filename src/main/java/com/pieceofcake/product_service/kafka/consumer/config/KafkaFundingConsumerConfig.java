package com.pieceofcake.product_service.kafka.consumer.config;

import com.pieceofcake.product_service.kafka.consumer.event.FundingReadEvent;
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
public class KafkaFundingConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    /**
     * 공통 consumer config 설정
     */
    @Bean
    public Map<String, Object> fundingConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "product-create-funding-group");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, FundingReadEvent.class); // 필요시 ProductEvent로 수정

        return props;
    }

    /**
     * Kafka ConsumerFactory 설정
     */
    @Bean
    public ConsumerFactory<String, FundingReadEvent> fundingConsumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                fundingConsumerConfigs(),
                new StringDeserializer(),
                new ErrorHandlingDeserializer<>(new JsonDeserializer<>(FundingReadEvent.class, false))
        );
    }

    /**
     * KafkaListenerContainerFactory 설정
     */
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FundingReadEvent> fundingEventListener() {
        ConcurrentKafkaListenerContainerFactory<String, FundingReadEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(fundingConsumerFactory());
        return factory;
    }

}
