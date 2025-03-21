package com.airalo.api.pojo.sims;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "meta"
})
@Getter
@Setter
public class ValidationErrorResponse {

    @JsonProperty("data")
    private Map<String, String> data;

    @JsonProperty("meta")
    private Meta meta;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}
