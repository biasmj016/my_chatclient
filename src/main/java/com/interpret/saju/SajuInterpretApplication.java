package com.interpret.saju;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SajuInterpretApplication {

    public static void main(String[] args) {
        SpringApplication.run(SajuInterpretApplication.class, args);
    }

}
