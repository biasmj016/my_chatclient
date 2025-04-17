package com.interpret.saju.controller.dto;

import com.interpret.saju.domain.Saju;

import java.time.LocalDate;

public record SajuRequest(
        int year,
        int month,
        int day,
        int hour,
        int minute,
        String gender,
        boolean isSolar,
        boolean leapMonth,
        String name,
        String birthplace  // 선택값
) {
    public SajuRequest {
        validateNotBlank(gender, "Gender is required and must not be blank.");
        validateNotBlank(name, "Name is required and must not be blank.");
        validateRange(year, 1990, LocalDate.now().getYear(), "Year must be between 1990 and the current year.");
        validateRange(month, 1, 12, "Month must be between 1 and 12.");
        validateRange(day, 1, 31, "Day must be between 1 and 31.");
        validateRange(hour, 0, 23, "Hour must be between 0 and 23.");
        validateRange(minute, 0, 59, "Minute must be between 0 and 59.");
    }

    public Saju toDomain() {
        return new Saju(
                year,
                month,
                day,
                hour,
                minute,
                gender,
                isSolar,
                leapMonth,
                name,
                birthplace
        );
    }

    private void validateNotBlank(String value, String errorMessage) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateRange(int value, int min, int max, String errorMessage) {
        if (value < min || value > max) {
            throw new IllegalArgumentException(errorMessage);
        }
    }
}