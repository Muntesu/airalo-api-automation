package com.airalo.api.pojo.sims;

import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "first",
    "last",
    "prev",
    "next"
})
@Getter
@Setter
public class Links {

    @JsonProperty("first")
    private String first;
    @JsonProperty("last")
    private String last;
    @JsonProperty("prev")
    private String prev;
    @JsonProperty("next")
    private String next;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}

