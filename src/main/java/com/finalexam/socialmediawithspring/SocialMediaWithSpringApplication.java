package com.finalexam.socialmediawithspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"Controller","Service","Repository","Model","config"})
public class SocialMediaWithSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(SocialMediaWithSpringApplication.class, args);
    }

}
