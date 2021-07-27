package com.example.servicesconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ServicesConfigApplication {

    public static void main(String[] args) {
	SpringApplication.run(ServicesConfigApplication.class, args);
    }
}
