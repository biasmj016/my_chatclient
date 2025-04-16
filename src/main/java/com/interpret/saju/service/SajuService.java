package com.interpret.saju.service;

import com.interpret.saju.domain.Saju;
import com.interpret.saju.infrastructure.client.OpenAiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SajuService {
    private final OpenAiClient openAiClient;

    @Cacheable(value = "sajuInterpretations", key = "#saju.getKey()")
    public String interpret(Saju saju) throws Exception {
        return openAiClient.execute(saju);
    }
}
