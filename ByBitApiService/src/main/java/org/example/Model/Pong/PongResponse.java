package org.example.Model.Pong;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PongResponse(
        @JsonProperty("success") boolean success,
        @JsonProperty("ret_msg") String retMsg,
        @JsonProperty("conn_id") String connId,
        @JsonProperty("req_id") String reqId,
        @JsonProperty("op") String op
) {
}
