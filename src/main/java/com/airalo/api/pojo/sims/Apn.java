package com.airalo.api.pojo.sims;


import com.fasterxml.jackson.annotation.*;
import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "ios",
    "android"
})
@Getter
@Setter
public class Apn {

    @JsonProperty("ios")
    private ApnConfig ios;

    @JsonProperty("android")
    private ApnConfig android;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}

