package com.pieceofcake.product_service.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "openai.api")
public class OpenAiProperties {
    private String key;
    private String model;
    private double temperature;
    private int maxTokens;
    private Url url;

    @Data
    public static class Url {
        private String model;
        private String modelList;
        private String prompt;
    }
}