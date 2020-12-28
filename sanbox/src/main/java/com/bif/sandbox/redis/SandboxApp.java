package com.bif.sandbox.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SandboxApp {
    public static void main(String[] args) {
        SpringApplication.run(SandboxApp.class, args);
    }

}
