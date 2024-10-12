package org.example.Model.Ticker;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TickerResponse(
        @JsonProperty("retCode") int retCode,
        @JsonProperty("retMsg") String retMsg,
        @JsonProperty("result") TickerResult tickerResult,
        @JsonProperty("retExtInfo") Object retExtInfo,
        @JsonProperty("time") long time
) {




}



