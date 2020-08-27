package com.ocs.backend.utils;

import lombok.SneakyThrows;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
	private static final String SYSTEM = "system.properties";
	private static final String LOCAL = "local.properties";
	private static final String STG = "stg.properties";

	public static String getSystemProperty(String property) {
		return getProperty(SYSTEM, property);
	}

	public static String getLocalProperty(String property) {
		return getProperty(LOCAL, property);
	}

	public static String getStgProperty(String property) {
		return getProperty(STG, property);
	}

	public static String getProperty(Environment env, String property) {
		return LOCAL.startsWith(env.getEnvName()) ? getLocalProperty(property) : getStgProperty(property);
	}

	@SneakyThrows
	private static String getProperty(String resource, String property) {
		InputStream resourcePath = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
		Properties appProps = new Properties();
		appProps.load(resourcePath);

		return appProps.getProperty(property);
	}
}
