package com.tests.helpers;

import java.util.Properties;

public class PropertiesReader {

    private static final String DEFAULT_RESOURCE_PATH = "test.properties";

    public Properties readProperties() {
        return readProperties(DEFAULT_RESOURCE_PATH);
    }

    public Properties readProperties(String resourcePath) {
        return null; // todo load from class path resources
    }
}
