package com.interpret.saju.service;

import com.interpret.saju.domain.Saju;
import com.interpret.saju.infrastructure.client.OpenAiClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SajuServiceTest {

    private OpenAiClient openAiClient;
    private SajuService service;

    @BeforeEach
    public void setUp() {
        openAiClient = Mockito.mock(OpenAiClient.class);
        service = new SajuService(openAiClient);
    }

    @Test
    public void interpret() throws Exception {
        Saju saju = new Saju(1990, 5, 15, 14, "남성", true, false, "홍길동", null);
        String expectedInterpretation = "테스트 사주 풀이 결과";
        Mockito.when(openAiClient.execute(saju)).thenReturn(expectedInterpretation);
        String result = service.interpret(saju);
        assertEquals(expectedInterpretation, result);
    }
}