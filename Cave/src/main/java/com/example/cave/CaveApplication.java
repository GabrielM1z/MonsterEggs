package com.example.cave;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CaveApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaveApplication.class, args);
    }

}
