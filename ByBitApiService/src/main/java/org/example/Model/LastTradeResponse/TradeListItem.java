package org.example.Model.LastTradeResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TradeListItem(
        @JsonProperty("T") long timestamp,
        @JsonProperty("s") String symbol,
        @JsonProperty("S") String side,
        @JsonProperty("v") String volume,
        @JsonProperty("p") String price,
        @JsonProperty("L") String direction,
        @JsonProperty("i") String id,
        @JsonProperty("BT") boolean isBlockTrade) {}
