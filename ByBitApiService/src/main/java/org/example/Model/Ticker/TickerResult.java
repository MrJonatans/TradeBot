package org.example.Model.Ticker;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TickerResult(
        @JsonProperty("category") String category,
        @JsonProperty("list") List<TickerListItem> responseEntity
) {}