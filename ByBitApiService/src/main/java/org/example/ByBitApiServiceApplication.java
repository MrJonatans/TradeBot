package org.example;


import org.example.Config.ApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class ByBitApiServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ByBitApiServiceApplication.class, args);
    }
}