package com.interpret.saju.service;

import com.interpret.saju.domain.Saju;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SajuInterpretationService {

    @Cacheable(value = "sajuInterpretations", key = "#saju.getKey()")
    public String interpret(Saju saju)  {
        return "";
    }
}
