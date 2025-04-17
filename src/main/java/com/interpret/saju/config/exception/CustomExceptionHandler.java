package com.interpret.saju.config.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.retry.NonTransientAiException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {
    private final ObjectMapper mapper = new ObjectMapper();

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handle(IllegalArgumentException ex) {
        log.error("Validation failed", ex);
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponse(BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(NonTransientAiException.class)
    public ResponseEntity<ErrorResponse> handle(NonTransientAiException ex) {
        log.error("AI service error", ex);
        String message = extractError(ex.getMessage());
        return ResponseEntity
                .status(INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(INTERNAL_SERVER_ERROR.value(), message));
    }

    private String extractError(String json) {
        try {
            var payload = json.contains("{") ? json.substring(json.indexOf('{')) : "{}";
            var msg = mapper.readTree(payload)
                    .path("error")
                    .path("message")
                    .asText()
                    .trim();
            return msg.isEmpty() ? "An unknown error occurred." : msg;
        } catch (Exception e) {
            log.error("Failed to parse error payload", e);
            return "An unknown error occurred.";
        }
    }

    public record ErrorResponse(int status, String message) {
    }
}
