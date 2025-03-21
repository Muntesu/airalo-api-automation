package com.airalo.api.pojo.orders;

import com.airalo.api.pojo.sims.Meta;
import com.fasterxml.jackson.annotation.*;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "meta"
})
@Data
public class SimPurchaseResponse {

    @JsonProperty("data")
    private SimPurchaseData data;

    @JsonProperty("meta")
    private Meta meta;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("data")
    public SimPurchaseData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(SimPurchaseData data) {
        this.data = data;
    }

    @JsonProperty("meta")
    public Meta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}

