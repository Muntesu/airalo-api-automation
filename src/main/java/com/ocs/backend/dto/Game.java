package com.ocs.backend.dto;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"game_id",
		"city",
		"year"
})
public class Game {

	@JsonProperty("game_id")
	private Long gameId;
	@JsonProperty("city")
	private String city;
	@JsonProperty("year")
	private Long year;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("game_id")
	public Long getGameId() {
		return gameId;
	}

	@JsonProperty("game_id")
	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public Game withGameId(Long gameId) {
		this.gameId = gameId;
		return this;
	}

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	public Game withCity(String city) {
		this.city = city;
		return this;
	}

	@JsonProperty("year")
	public Long getYear() {
		return year;
	}

	@JsonProperty("year")
	public void setYear(Long year) {
		this.year = year;
	}

	public Game withYear(Long year) {
		this.year = year;
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

	public Game withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("gameId", gameId).append("city", city).append("year", year).append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(gameId).append(additionalProperties).append(city).append(year).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Game) == false) {
			return false;
		}
		Game rhs = ((Game) other);
		return new EqualsBuilder().append(gameId, rhs.gameId).append(additionalProperties, rhs.additionalProperties).append(city, rhs.city).append(year, rhs.year).isEquals();
	}

}
