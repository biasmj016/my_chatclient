package com.interpret.saju.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SajuTest {
    @Test
    public void getKey() {
        Saju saju = new Saju(1990, 5, 15, 14, "남성", true, true, "홍길동", "서울");
        String expectedKey = "1990_5_15_14_남성_true_true_홍길동_서울";
        assertEquals(expectedKey, saju.getKey());
    }
    @Test
    public void getKey_optional() {
        Saju saju = new Saju(1990, 5, 15, 14, "남성", true, true, "홍길동", null);
        String expectedKey = "1990_5_15_14_남성_true_true_홍길동";
        assertEquals(expectedKey, saju.getKey());
    }
}