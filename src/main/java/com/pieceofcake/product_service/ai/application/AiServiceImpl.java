package com.pieceofcake.product_service.ai.application;

import com.pieceofcake.product_service.ai.dto.in.AiRequestDto;
import com.pieceofcake.product_service.ai.dto.out.AiResponseDto;
import com.pieceofcake.product_service.common.config.OpenAiProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Slf4j
@Service
public class AiServiceImpl implements AiService{
    private final RestTemplate restTemplate;
//    private final HttpHeaders httpHeaders;
    private final OpenAiProperties properties;

//    public AiServiceImpl(RestTemplate restTemplate, HttpHeaders httpHeaders, OpenAiProperties properties) {
    public AiServiceImpl(RestTemplate restTemplate, OpenAiProperties properties) {
        this.restTemplate = restTemplate;
//        this.httpHeaders = httpHeaders;
        this.properties = properties;
    }

    @Value("${openai.api.url.model}")
    private String modelUrl;

    @Value("${openai.api.url.model-list}")
    private String modelListUrl;

    @Value("${openai.api.url.prompt}")
    private String promptUrl;
    /**
     * 사용 가능한 모델 리스트를 조회하는 비즈니스 로직
     *
     * @return List<Map < String, Object>>
     */
    @Override
    public Map<String, Object> modelList() {
//        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(properties.getKey());
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                modelListUrl,
                HttpMethod.GET,
                entity,
                Map.class
        );

        return response.getBody();
    }

    /**
     * 실제 응답을 받아오는 api
     */
    public AiResponseDto getAnswer(AiRequestDto aiRequestDto) {

        // 1. System 역할: 응답 형식 및 역할 정의
        Map<String, Object> systemMessage = new HashMap<>();
        systemMessage.put("role", "system");
        systemMessage.put("content", "당신은 희귀 및 명품 물품의 가격 책정 전문가입니다. 이미지와 설명을 바탕으로 이 물품의 한국 원화 예상 가격을 숫자로만 추정해주세요. 이후 그 예상가를 선정한 이유를 짧게 한 문장으로 설명하세요. 해당 신상품의 가격, 비슷한 대조군 등을 예시에 포함하면 좋습니다. 예: \"32,000. 이 물품은 한정판으로, 현재 시장에서 비슷한 제품의 가격이 30,000원에서 35,000원 사이입니다. 따라서 이 물품의 예상가는 32,000원입니다.\"");

        // 2. User 역할: 텍스트 설명과 이미지 함께 제공
        Map<String, Object> userMessage = Map.of(
                "role", "user",
                "content", List.of(
                        Map.of("type", "text", "text", aiRequestDto.getDescription()),
                        Map.of("type", "image_url", "image_url", Map.of("url", aiRequestDto.getImageUrl()))
                )
        );
        // 3. 전체 요청 구성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", properties.getModel()); // "gpt-4o" 또는 "gpt-4-vision-preview"
        requestBody.put("temperature", properties.getTemperature());
        requestBody.put("messages", List.of(systemMessage, userMessage));
        requestBody.put("max_tokens", properties.getMaxTokens());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(properties.getKey());

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        ResponseEntity<Map> response = restTemplate.exchange(
                promptUrl,
                HttpMethod.POST,
                entity,
                Map.class
        );

        List<Map<String, Object>> choices = (List<Map<String, Object>>) response.getBody().get("choices");
        Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
        String content = (String) message.get("content");

        return new AiResponseDto(properties.getModel(), content.trim());
    }


}
