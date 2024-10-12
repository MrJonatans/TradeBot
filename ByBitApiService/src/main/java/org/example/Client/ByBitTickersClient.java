package org.example.Client;

import org.example.Model.Ticker.Order.OrderResponse;
import org.example.Model.Ticker.TickerResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


public class ByBitTickersClient {

        WebClient webClient;

    public ByBitTickersClient(WebClient webClient) {
        this.webClient = webClient;
    }


    public OrderResponse getOrderBook(String tickerCategory, String tickerSymbol){
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/orderbook")
                        .queryParam("category", tickerCategory)
                        .queryParam("symbol", tickerSymbol)
                        .build())

                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("API not found")))

                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("Server is not responding")))

                .bodyToMono(OrderResponse.class)
                .block();
    }


    public TickerResponse getTicker(String tickerCategory, String tickerSymbol){
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/tickers")
                        .queryParam("category", tickerCategory)
                        .queryParam("symbol", tickerSymbol)
                        .build())

                .retrieve()
                .onStatus(
                        HttpStatusCode::is4xxClientError,
                        error -> Mono.error(new RuntimeException("API not found")))

                .onStatus(
                        HttpStatusCode::is5xxServerError,
                        error -> Mono.error(new RuntimeException("Server is not responding")))

                .bodyToMono(TickerResponse.class)
                .block();
    }
}
