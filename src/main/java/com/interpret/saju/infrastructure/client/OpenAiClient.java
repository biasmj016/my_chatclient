package com.interpret.saju.infrastructure.client;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interpret.saju.domain.Saju;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class OpenAiClient {

    @Value("${openai.api.key}")
    private String apiKey;
    private static final String OPENAI_URL = "https://api.openai.com/v1/chat/completions";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    public String execute(Saju saju) throws Exception {
        String prompt = createPrompt(saju);
        Map<String, Object> requestBody = createRequestBody(prompt);
        HttpHeaders headers = createHeaders();

        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> responseEntity = restTemplate.postForEntity(OPENAI_URL, httpEntity, String.class);

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            JsonNode root = objectMapper.readTree(responseEntity.getBody());
            return root.path("choices").get(0).path("message").path("content").asText();
        } else {
            throw new RuntimeException("Failed to call OpenAI API: " + responseEntity.getStatusCode());
        }
    }

    private String createPrompt(Saju saju) {
        return String.format(
                "사주명리학 기준으로 아래 정보를 바탕으로 사주풀이 해석을 해줘:\n" +
                        "생년: %d, 생월: %d, 생일: %d, 출생 시간: %d\n" +
                        "성별: %s\n" +
                        "양력 여부: %s\n" +
                        "윤달 여부: %s\n" +
                        "이름: %s\n" +
                        "출생지: %s",
                saju.year(), saju.month(), saju.day(), saju.hour(),
                saju.gender(),
                saju.isSolar() ? "양력" : "음력",
                saju.leapMonth() ? "윤달" : "평달",
                saju.name(),
                saju.birthplace() != null ? saju.birthplace() : "-"
        );
    }

    private Map<String, Object> createRequestBody(String prompt) {
        Map<String, Object> message = Map.of(
                "role", "user",
                "content", prompt
        );
        return Map.of(
                "model", "gpt-4.1",
                "messages", List.of(message),
                "temperature", 0.7
        );
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey);
        headers.set("Content-Type", "application/json");
        return headers;
    }
}