package org.example.Model.Ticker.Order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrderResult(

        @JsonProperty("s") String symbol,
        @JsonProperty("b") List<List<String>> bids,
        @JsonProperty("a") List<List<String>> asks,
        @JsonProperty("ts") long timestamp,
        @JsonProperty("u") long updateId,
        @JsonProperty("seq") long sequence,
        @JsonProperty("cts") long creationTimestamp
) {
}
