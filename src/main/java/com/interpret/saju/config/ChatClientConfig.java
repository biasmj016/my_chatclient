package com.interpret.saju.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatClientConfig {
    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder
                .defaultSystem("당신은 사주명리학 전문가입니다. 아래 정보를 바탕으로 사주풀이를 해석해 주세요:")
                .build();
    }
}