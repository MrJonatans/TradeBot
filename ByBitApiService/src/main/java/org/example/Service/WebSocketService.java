package org.example.Service;


import lombok.extern.slf4j.Slf4j;
import org.example.Handler.WebSocketHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

import java.util.*;


@Slf4j
@Service
@EnableWebSocket
@Configuration
public class WebSocketService {

    @Autowired
    WebSocketConnectionManager manager;
    @Autowired
    WebSocketHandler handler;
    Map<String,HashSet<String>> subs = new HashMap<>();

    public void connect() {
        log.info("Connecting to WebSocket Server");
        manager.start();
    }

    public  void reconnect(){
        log.info("RECONNECTING to WebSocket Server");
        manager.stop();
        manager.start();
        subs.forEach((key, value) -> value.forEach(y -> subscribe(key, y)));
    }

    public void subscribe(String operation, String symbol) {

        if (subs.containsKey(operation)) subs.get(operation).add(symbol);
        else {
            HashSet<String> set = new HashSet<>();
            set.add(symbol);
            subs.put(operation,set);
        }
        log.info("Subscribing to topic: " + operation + "\\" + symbol);
        String subscribeMessage = "{\"op\": \"subscribe\", \"args\": [\"" + operation + "." + symbol + "\"]}";
        handler.subscribeToTopic(subscribeMessage);
    }


}
