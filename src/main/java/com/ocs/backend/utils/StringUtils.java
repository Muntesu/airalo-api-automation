package com.ocs.backend.utils;

public class StringUtils {
	public static String formatPath(String path, String id) {
		return path.replaceAll("\\{id}", id);
	}
}
