package com.andrei.latishko.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static final String DEFAULT_RESOURCE_PATH = "application.properties";

    public Properties readProperties() throws IOException {
        return readProperties(DEFAULT_RESOURCE_PATH);
    }

    public Properties readProperties(String resourcePath) throws IOException {
        Properties properties;
        try (FileInputStream fileInputStream = new FileInputStream(resourcePath)) {
            properties = new Properties();
            properties.load(fileInputStream);
        }
        return properties;
    }
}
