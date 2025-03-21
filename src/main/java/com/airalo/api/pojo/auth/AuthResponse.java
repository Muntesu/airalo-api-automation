package com.airalo.api.pojo.auth;


import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "data",
    "meta"
})
public class AuthResponse {

    @JsonProperty("data")
    private AuthData data;

    @JsonProperty("meta")
    private AuthMeta meta;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("data")
    public AuthData getData() {
        return data;
    }

    @JsonProperty("data")
    public void setData(AuthData data) {
        this.data = data;
    }

    public AuthResponse withData(AuthData data) {
        this.data = data;
        return this;
    }

    @JsonProperty("meta")
    public AuthMeta getMeta() {
        return meta;
    }

    @JsonProperty("meta")
    public void setMeta(AuthMeta meta) {
        this.meta = meta;
    }

    public AuthResponse withMeta(AuthMeta meta) {
        this.meta = meta;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public AuthResponse withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .append("data", data)
            .append("meta", meta)
            .append("additionalProperties", additionalProperties)
            .toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(data).append(meta).append(additionalProperties)
            .toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AuthResponse)) {
            return false;
        }
        AuthResponse rhs = (AuthResponse) other;
        return new EqualsBuilder().append(data, rhs.data).append(meta, rhs.meta)
            .append(additionalProperties, rhs.additionalProperties).isEquals();
    }
}

