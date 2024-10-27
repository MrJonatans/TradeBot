package org.example.Handler;

import ch.qos.logback.core.net.SocketConnector;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.Model.LastTradeResponse.TradeResponse;
import org.example.Model.Pong.PingRequest;
import org.example.Model.Pong.PongResponse;
import org.example.Service.WebSocketService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Getter
@Slf4j
public class WebSocketHandler extends TextWebSocketHandler {


    @Lazy
    @Autowired
    private WebSocketService connector;
    private volatile WebSocketSession session;

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final PingRequest pingRequest = new PingRequest("10001", "ping");

    private final List<TradeResponse> tradeResponses = new ArrayList<>(); //remove


    @Override
    public void afterConnectionEstablished(@NotNull WebSocketSession session) {
        log.info("WebSocket connection established");
        this.session = session;
        session.setTextMessageSizeLimit(4096 * 4096); //maby bigger later

    }

    @Override
    public void afterConnectionClosed(@NotNull WebSocketSession session, @NotNull CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        if (status.getCode() == 1006 || status.getCode() == 1011 || status.getCode() == 1012) {
            connector.reconnect();
        }
    }


    @Override
    public void handleTextMessage(@NotNull WebSocketSession session, TextMessage message) throws JsonProcessingException {
        synchronized (session) { //session is not multi-threadable so...
            if (message.getPayload().contains("ret_msg")) {
                PongResponse response = objectMapper.readValue(message.getPayload(), PongResponse.class);
                log.info("------------------------- : {}", response.retMsg().isEmpty() ? response.op() : response.retMsg());
            } else {
                TradeResponse response = objectMapper.readValue(message.getPayload(), TradeResponse.class);
                tradeResponses.add(response);
//                log.info("Received text message: {}", response.topic());  // you can transfer data from here
            }
        }

    }


    public void subscribeToTopic(String topic) {
        if (session != null) {
            try {
                session.sendMessage(new TextMessage(topic));
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        } else {
            System.out.println("WebSocket session is not established.");
        }
    }


    @Scheduled(fixedDelayString = "#{scheduler.interval}")
    public void sendHeartbeatMessage() {
        log.info(String.valueOf(tradeResponses.size())); //remove
        if (session != null) {
            synchronized (session) {
                try {
                    log.info("------------------------- : ping");
                    session.sendMessage(new TextMessage(pingRequest.toJson()));
                } catch (Exception e) {
                    log.info(e.getMessage());
                }
            }
        }
    }

}
