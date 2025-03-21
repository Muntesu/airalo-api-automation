package com.airalo.api.utils;

public class StringUtils {
	public static String formatPath(String path, String id) {
		return path.replaceAll("\\{id}", id);
	}
}
