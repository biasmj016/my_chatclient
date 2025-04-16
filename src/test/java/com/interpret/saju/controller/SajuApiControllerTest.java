package com.interpret.saju.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interpret.saju.controller.dto.SajuRequest;
import com.interpret.saju.domain.Saju;
import com.interpret.saju.service.SajuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class SajuApiControllerTest {

    private MockMvc mockMvc;
    @Mock
    private SajuService sajuService;

    @InjectMocks
    private SajuApiController controller;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void interpret() throws Exception {
        SajuRequest request = new SajuRequest(1990, 5, 15, 14, "남성", true, true, "홍길동", "서울");
        String expectedInterpretation = "통합 테스트 사주풀이 결과";

        Mockito.when(sajuService.interpret(Mockito.any(Saju.class)))
                .thenReturn(expectedInterpretation);

        mockMvc.perform(post("/api/saju/interpret")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.interpretation").value(expectedInterpretation))
                .andExpect(jsonPath("$.name").value("홍길동"));
    }
}