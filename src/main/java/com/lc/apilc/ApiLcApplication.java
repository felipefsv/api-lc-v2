package com.lc.apilc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication()
public class ApiLcApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiLcApplication.class, args);
    }

    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("######### RUNNING ############");
        };
    }
}
