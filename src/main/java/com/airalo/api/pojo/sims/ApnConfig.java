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
    "apn_type",
    "apn_value"
})
@Getter
@Setter
public class ApnConfig {

    @JsonProperty("apn_type")
    private String apnType;

    @JsonProperty("apn_value")
    private String apnValue;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}

