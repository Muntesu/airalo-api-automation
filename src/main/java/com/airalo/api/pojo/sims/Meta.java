package com.airalo.api.pojo.sims;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "message",
    "current_page",
    "from",
    "last_page",
    "path",
    "per_page",
    "to",
    "total"
})
@Data
public class Meta {

    @JsonProperty("message")
    private String message;

    @JsonProperty("current_page")
    private Integer currentPage;

    @JsonProperty("from")
    private Integer from;

    @JsonProperty("last_page")
    private Integer lastPage;

    @JsonProperty("path")
    private String path;

    @JsonProperty("per_page")
    private String perPage;

    @JsonProperty("to")
    private Integer to;

    @JsonProperty("total")
    private Integer total;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

}

