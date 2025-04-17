package com.interpret.saju.domain;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public record Saju(
        int year,
        int month,
        int day,
        int hour,
        int minute,
        String gender,
        boolean isSolar,         // true: 양력, false: 음력
        boolean leapMonth,  // 윤달 여부
        String name,
        String birthplace        // 선택 사항
) {

    public String getKey() {
        return Stream.of(
                        String.valueOf(year),
                        String.format("%02d", month),
                        String.format("%02d", day),
                        String.format("%02d", hour),
                        String.format("%02d", minute),
                        gender,
                        Boolean.toString(isSolar),
                        Boolean.toString(leapMonth),
                        name,
                        birthplace
                )
                .filter(s -> s != null && !s.isBlank())
                .collect(Collectors.joining("_"));
    }
}