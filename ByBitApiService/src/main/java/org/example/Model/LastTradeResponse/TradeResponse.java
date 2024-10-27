package org.example.Model.LastTradeResponse;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TradeResponse(
        @JsonProperty("topic") String topic,
        @JsonProperty("type") String type,
        @JsonProperty("ts") long ts,
        @JsonProperty("data") List<TradeListItem> data
){}