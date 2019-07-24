package com.opsigte.e.cache.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ECacheApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ECacheApiApplication.class, args);
    }

}
