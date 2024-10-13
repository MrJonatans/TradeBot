package org.example.Controller;


import org.example.Client.ByBitTickersClient;
import org.example.Model.Ticker.TickerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
@EnableScheduling
public class TickerScheduler {

    private final List<String> tickerSymbols = List.of("BTCUSDT", "DOGUSDT", "ETHUSDT", "TRBUSDT");
    private final String INVERSE = "inverse";


    @Autowired
    ByBitTickersClient byBitTickersClient;


    @Scheduled(fixedDelayString = "#{scheduler.interval}")
    public void get() {

        //could be other scheduled task which getting tickers out of DB
        List<CompletableFuture<TickerResponse>> futures = new ArrayList<>();

        for (var ticker : tickerSymbols) {
            futures.add(CompletableFuture.supplyAsync(() -> byBitTickersClient.getTicker(INVERSE, ticker)));
        }

        List<TickerResponse> tickers = futures.stream().map(CompletableFuture::join).toList();
        print(tickers);


    }


    //remove
    public void print(List<TickerResponse> tickers) {
        for (var ticker : tickers) {
            System.out.println(ticker.tickerResult().responseEntity());
            System.out.println("------------------------------------------------------------------------------------");
        }
    }

}
