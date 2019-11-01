package com.ruby.cyclone.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
public class CycloneServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CycloneServerApplication.class, args);
    }

}
