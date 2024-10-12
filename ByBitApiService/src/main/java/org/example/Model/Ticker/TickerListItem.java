package org.example.Model.Ticker;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TickerListItem(
        @JsonProperty("symbol") String symbol,
        @JsonProperty("lastPrice") String lastPrice,
        @JsonProperty("indexPrice") String indexPrice,
        @JsonProperty("markPrice") String markPrice,
        @JsonProperty("prevPrice24h") String prevPrice24h,
        @JsonProperty("price24hPcnt") String price24hPcnt,
        @JsonProperty("highPrice24h") String highPrice24h,
        @JsonProperty("lowPrice24h") String lowPrice24h,
        @JsonProperty("prevPrice1h") String prevPrice1h,
        @JsonProperty("openInterest") String openInterest,
        @JsonProperty("openInterestValue") String openInterestValue,
        @JsonProperty("turnover24h") String turnover24h,
        @JsonProperty("volume24h") String volume24h,
        @JsonProperty("fundingRate") String fundingRate,
        @JsonProperty("nextFundingTime") String nextFundingTime,
        @JsonProperty("predictedDeliveryPrice") String predictedDeliveryPrice,
        @JsonProperty("basisRate") String basisRate,
        @JsonProperty("deliveryFeeRate") String deliveryFeeRate,
        @JsonProperty("deliveryTime") String deliveryTime,
        @JsonProperty("ask1Size") String ask1Size,
        @JsonProperty("bid1Price") String bid1Price,
        @JsonProperty("ask1Price") String ask1Price,
        @JsonProperty("bid1Size") String bid1Size,
        @JsonProperty("basis") String basis,
        @JsonProperty("preOpenPrice") String preOpenPrice,
        @JsonProperty("preQty") String preQty,
        @JsonProperty("curPreListingPhase") String curPreListingPhase
) {}