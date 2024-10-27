package org.example;


import org.example.Config.ApplicationConfig;
import org.example.Service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ApplicationConfig.class)
public class ByBitApiServiceApplication implements CommandLineRunner {
    @Autowired
    private WebSocketService webSocketService;

    public static void main(String[] args) {
        SpringApplication.run(ByBitApiServiceApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        webSocketService.connect();
    }
}