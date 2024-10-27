package org.example.Controller;

import lombok.AllArgsConstructor;
import org.example.Service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class WebSocketController {

    @Autowired
    private WebSocketService webSocketService;

    @GetMapping("/subscribe")
    public String subscribeToTopic(@RequestParam String operation, @RequestParam String symbol) {
        webSocketService.subscribe(operation,symbol);
        return "Subscribed";
    }
}
