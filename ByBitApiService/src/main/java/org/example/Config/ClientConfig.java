package org.example.Config;


import org.example.Client.ByBitTickersClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Bean
    public ByBitTickersClient getByBitTickersClient(@Value("${app.base-url-bybit}") String baseUrl) {
        WebClient byBitWebClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        return new ByBitTickersClient(byBitWebClient);
    }

}
