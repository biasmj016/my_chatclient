package com.example.chatclient.config;

import java.time.Duration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.theokanning.openai.service.OpenAiService;

@Configuration
public class ChatClientConfiguration {

    @Value("${openai.api.key}")
    private String openaiApiKey;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(openaiApiKey, Duration.ofSeconds(30));
    }
}