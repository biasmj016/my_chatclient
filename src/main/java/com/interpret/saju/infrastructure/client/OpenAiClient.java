package com.interpret.saju.infrastructure.client;

import com.interpret.saju.domain.Saju;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenAiClient {

    private final ChatClient chatClient;

    public String execute(Saju saju) throws RuntimeException {
        String prompt = createPrompt(saju);
        log.info("아래 정보를 바탕으로 사주풀이가 진행됩니다. \n{}", prompt);
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    private String createPrompt(Saju saju) {
        return String.format(
                "출생 정보:\n" +
                        "- 생년월일: %d년 %02d월 %02d일 %02d시 %02d분\n" +
                        "- 성별: %s\n" +
                        "- 양력 여부: %s\n" +
                        "- 윤달 여부: %s\n" +
                        "- 이름: %s\n" +
                        "- 출생지: %s",
                saju.year(), saju.month(), saju.day(), saju.hour(), saju.minute(),
                saju.gender(),
                saju.isSolar() ? "양력" : "음력",
                saju.leapMonth() ? "윤달" : "평달",
                saju.name(),
                saju.birthplace() != null ? saju.birthplace() : "-"
        );
    }
}