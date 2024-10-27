package org.example.Model.Pong;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public record PingRequest(
        @JsonProperty("req_id") String reqId,
        @JsonProperty("op")  String op

) {
        public String toJson() throws JsonProcessingException {
                ObjectMapper objectMapper = new ObjectMapper();
                return objectMapper.writeValueAsString(this);
        }
}
