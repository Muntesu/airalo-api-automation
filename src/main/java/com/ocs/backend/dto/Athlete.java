package com.ocs.backend.dto;

import com.fasterxml.jackson.annotation.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.HashMap;
import java.util.Map;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"athlete_id",
		"name",
		"surname",
		"dateOfBirth",
		"bio",
		"weight",
		"height",
		"photo_id"
})
public class Athlete {

	@JsonProperty("athlete_id")
	private String athleteId;
	@JsonProperty("name")
	private String name;
	@JsonProperty("surname")
	private String surname;
	@JsonProperty("dateOfBirth")
	private String dateOfBirth;
	@JsonProperty("bio")
	private String bio;
	@JsonProperty("weight")
	private Long weight;
	@JsonProperty("height")
	private Long height;
	@JsonProperty("photo_id")
	private Long photoId;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

	@JsonProperty("athlete_id")
	public String getAthleteId() {
		return athleteId;
	}

	@JsonProperty("athlete_id")
	public void setAthleteId(String athleteId) {
		this.athleteId = athleteId;
	}

	public Athlete withAthleteId(String athleteId) {
		this.athleteId = athleteId;
		return this;
	}

	@JsonProperty("name")
	public String getName() {
		return name;
	}

	@JsonProperty("name")
	public void setName(String name) {
		this.name = name;
	}

	public Athlete withName(String name) {
		this.name = name;
		return this;
	}

	@JsonProperty("surname")
	public String getSurname() {
		return surname;
	}

	@JsonProperty("surname")
	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Athlete withSurname(String surname) {
		this.surname = surname;
		return this;
	}

	@JsonProperty("dateOfBirth")
	public String getDateOfBirth() {
		return dateOfBirth;
	}

	@JsonProperty("dateOfBirth")
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Athlete withDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
		return this;
	}

	@JsonProperty("bio")
	public String getBio() {
		return bio;
	}

	@JsonProperty("bio")
	public void setBio(String bio) {
		this.bio = bio;
	}

	public Athlete withBio(String bio) {
		this.bio = bio;
		return this;
	}

	@JsonProperty("weight")
	public Long getWeight() {
		return weight;
	}

	@JsonProperty("weight")
	public void setWeight(Long weight) {
		this.weight = weight;
	}

	public Athlete withWeight(Long weight) {
		this.weight = weight;
		return this;
	}

	@JsonProperty("height")
	public Long getHeight() {
		return height;
	}

	@JsonProperty("height")
	public void setHeight(Long height) {
		this.height = height;
	}

	public Athlete withHeight(Long height) {
		this.height = height;
		return this;
	}

	@JsonProperty("photo_id")
	public Long getPhotoId() {
		return photoId;
	}

	@JsonProperty("photo_id")
	public void setPhotoId(Long photoId) {
		this.photoId = photoId;
	}

	public Athlete withPhotoId(Long photoId) {
		this.photoId = photoId;
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

	public Athlete withAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
		return this;
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("athleteId", athleteId).append("name", name).append("surname", surname).append("dateOfBirth", dateOfBirth).append("bio", bio).append("weight", weight).append("height", height).append("photoId", photoId).append("additionalProperties", additionalProperties).toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(athleteId).append(surname).append(name).append(bio).append(weight).append(photoId).append(dateOfBirth).append(additionalProperties).append(height).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == this) {
			return true;
		}
		if ((other instanceof Athlete) == false) {
			return false;
		}
		Athlete rhs = ((Athlete) other);
		return new EqualsBuilder().append(athleteId, rhs.athleteId).append(surname, rhs.surname).append(name, rhs.name).append(bio, rhs.bio).append(weight, rhs.weight).append(photoId, rhs.photoId).append(dateOfBirth, rhs.dateOfBirth).append(additionalProperties, rhs.additionalProperties).append(height, rhs.height).isEquals();
	}

}
