package com.interpret.saju.controller.dto;

import com.interpret.saju.domain.Saju;

public record SajuRequest(
        int year,
        int month,
        int day,
        int hour,
        String gender,
        boolean isSolar,
        boolean leapMonth,
        String name,
        String birthplace  // 선택값
) {
    public SajuRequest {
        if (gender == null || gender.isBlank()) {
            throw new IllegalArgumentException("Gender is required and must not be blank.");
        }
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name is required and must not be blank.");
        }
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("Month must be between 1 and 12.");
        }
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("Day must be between 1 and 31.");
        }
        if (hour < 0 || hour > 23) {
            throw new IllegalArgumentException("Hour must be between 0 and 23.");
        }
    }

    public Saju toDomain() {
        return new Saju(
                year,
                month,
                day,
                hour,
                gender,
                isSolar,
                leapMonth,
                name,
                birthplace
        );
    }
}