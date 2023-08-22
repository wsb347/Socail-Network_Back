package com.projectver2.spring.sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class SnsApplication {

    public static void main(String[] args) {

        SpringApplication.run(SnsApplication.class, args);
    }

}
