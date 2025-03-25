package com.airalo.api.configuration;

import org.aeonbits.owner.ConfigCache;

public class ConfigurationManager {

    private ConfigurationManager() {
    }

    public static Configuration getConfig() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
