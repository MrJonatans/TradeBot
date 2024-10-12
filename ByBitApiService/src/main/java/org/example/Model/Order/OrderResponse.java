package org.example.Model.Order;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderResponse(
        @JsonProperty("retCode") int retCode,
        @JsonProperty("retMsg") String retMsg,
        @JsonProperty("result") OrderResult result,
        @JsonProperty("retExtInfo") Object retExtInfo,
        @JsonProperty("time") long time
) {
}
