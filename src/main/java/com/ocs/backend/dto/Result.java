package com.ocs.backend.dto;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"city",
		"year",
		"gold",
		"silver",
		"bronze"
})
public class Result {

	@JsonProperty("city")
	private String city;
	@JsonProperty("year")
	private Long year;
	@JsonProperty("gold")
	private Long gold;
	@JsonProperty("silver")
	private Long silver;
	@JsonProperty("bronze")
	private Long bronze;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("city")
	public String getCity() {
		return city;
	}

	@JsonProperty("city")
	public void setCity(String city) {
		this.city = city;
	}

	public Result withCity(String city) {
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

	public Result withYear(Long year) {
		this.year = year;
		return this;
	}

	@JsonProperty("gold")
	public Long getGold() {
		return gold;
	}

	@JsonProperty("gold")
	public void setGold(Long gold) {
		this.gold = gold;
	}

	public Result withGold(Long gold) {
		this.gold = gold;
		return this;
	}

	@JsonProperty("silver")
	public Long getSilver() {
		return silver;
	}

	@JsonProperty("silver")
	public void setSilver(Long silver) {
		this.silver = silver;
	}

	public Result withSilver(Long silver) {
		this.silver = silver;
		return this;
	}

	@JsonProperty("bronze")
	public Long getBronze() {
		return bronze;
	}

	@JsonProperty("bronze")
	public void setBronze(Long bronze) {
		this.bronze = bronze;
	}

	public Result withBronze(Long bronze) {
		this.bronze = bronze;
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

	public Result withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("city", city).append("year", year).append("gold", gold).append("silver", silver).append("bronze", bronze).append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(gold).append(city).append(year).append(silver).append(additionalProperties).append(bronze).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Result) == false) {
			return false;
		}
		Result rhs = ((Result) other);
		return new EqualsBuilder().append(gold, rhs.gold).append(city, rhs.city).append(year, rhs.year).append(silver, rhs.silver).append(additionalProperties, rhs.additionalProperties).append(bronze, rhs.bronze).isEquals();
	}

}
