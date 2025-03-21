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
    "token_type",
    "expires_in",
    "access_token"
})
public class AuthData {

    @JsonProperty("token_type")
    private String tokenType;

    @JsonProperty("expires_in")
    private Long expiresIn;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonProperty("token_type")
    public String getTokenType() {
        return tokenType;
    }

    @JsonProperty("token_type")
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public AuthData withTokenType(String tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    @JsonProperty("expires_in")
    public Long getExpiresIn() {
        return expiresIn;
    }

    @JsonProperty("expires_in")
    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public AuthData withExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
        return this;
    }

    @JsonProperty("access_token")
    public String getAccessToken() {
        return accessToken;
    }

    @JsonProperty("access_token")
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public AuthData withAccessToken(String accessToken) {
        this.accessToken = accessToken;
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

    public AuthData withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("tokenType", tokenType)
            .append("expiresIn", expiresIn).append("accessToken", accessToken)
            .append("additionalProperties", additionalProperties).toString();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(tokenType).append(expiresIn).append(accessToken)
            .append(additionalProperties).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof AuthData)) {
            return false;
        }
        AuthData rhs = (AuthData) other;
        return new EqualsBuilder().append(tokenType, rhs.tokenType).append(expiresIn, rhs.expiresIn)
            .append(accessToken, rhs.accessToken)
            .append(additionalProperties, rhs.additionalProperties).isEquals();
    }
}

