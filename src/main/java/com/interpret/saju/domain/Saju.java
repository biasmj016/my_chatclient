package com.interpret.saju.domain;

import java.util.ArrayList;

public record Saju(
        int year,
        int month,
        int day,
        int hour,
        String gender,
        boolean isSolar,         // true: 양력, false: 음력
        boolean leapMonth,  // 윤달 여부
        String name,             // 선택 사항
        String birthplace        // 선택 사항
) {

    public String getKey() {
        var parts = new ArrayList<String>();
        parts.add(String.valueOf(year));
        parts.add(String.valueOf(month));
        parts.add(String.valueOf(day));
        parts.add(String.valueOf(hour));
        parts.add(gender);
        parts.add(String.valueOf(isSolar));
        parts.add(String.valueOf(leapMonth));
        if (name != null && !name.isBlank()) parts.add(name);
        if (birthplace != null && !birthplace.isBlank()) parts.add(birthplace);
        return String.join("_", parts);
    }
}