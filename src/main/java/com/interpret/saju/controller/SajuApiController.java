package com.interpret.saju.controller;

import com.interpret.saju.controller.dto.SajuRequest;
import com.interpret.saju.controller.dto.SajuResponse;
import com.interpret.saju.service.SajuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/saju")
@RequiredArgsConstructor
public class SajuApiController {
    private final SajuService sajuService;

    @PostMapping("/interpret")
    public ResponseEntity<SajuResponse> interpret(@RequestBody SajuRequest request) throws Exception {
        String interpretation = sajuService.interpret(request.toDomain());
        return ResponseEntity.ok(new SajuResponse(request.name(), interpretation));
    }
}
