package com.ocs.backend.api;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Endpoint {
	ATHLETES("/athletes"),
	AN_ATHLETE("/athletes/{id}"),
	ATHLETE_RESULTS("/athletes/{id}/results"),
	ATHLETE_PHOTO("/athletes/{id}/photo"),
	GAMES("games"),
	ATHLETE_IN_GAME("/games/{id}/athletes");

	@Getter
	private final String label;

}
