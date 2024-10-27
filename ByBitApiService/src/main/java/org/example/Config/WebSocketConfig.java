package org.example.Config;

import org.example.Handler.WebSocketHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

@Configuration
@EnableScheduling
public class WebSocketConfig  {

    @Bean
    public WebSocketHandler myWebSocketHandler() {
        return new WebSocketHandler();
    }


    @Bean
    public WebSocketConnectionManager getConnectionManager(@Value("${app.base-url-bybit}") String baseUrl, WebSocketHandler myWebSocketHandler) {
        WebSocketClient ws = new StandardWebSocketClient();
        return  new WebSocketConnectionManager(ws,myWebSocketHandler,baseUrl);
    }
}
