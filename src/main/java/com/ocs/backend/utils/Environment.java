package com.ocs.backend.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
public enum Environment {
	LOCAL("local"), STG("stg");

	private static final Map<String, Environment> BY_LABEL = new HashMap<>();

	static {
		for (Environment e : values()) {
			BY_LABEL.put(e.envName, e);
		}
	}

	@Getter
	private final String envName;

	public static Environment valueOfLabel(String label) {
		return BY_LABEL.get(label.toLowerCase());
	}
}
