package org.example.Controller;


import org.example.Client.ByBitTickersClient;
import org.example.Model.Order.OrderResponse;
import org.example.Model.Ticker.TickerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class TickerScheduler {

    //Удолите
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";


    @Autowired
    ByBitTickersClient byBitTickersClient;


    @Scheduled(fixedDelayString = "#{scheduler.interval}")
    public void get() {
    TickerResponse ticker = byBitTickersClient.getTicker("inverse","BTCUSDT");
    OrderResponse orders = byBitTickersClient.getOrderBook("inverse","BTCUSDT");

        //Удолите
    var asks = orders.result().asks();
    var bids = orders.result().bids();
        System.out.print("\033[H\033[J");
    for(var x : bids){
        System.out.println(ANSI_RED+x+ANSI_RESET);
    }
        for(var x : asks){
            System.out.println(ANSI_GREEN+x+ANSI_RESET);
        }

    }
}
