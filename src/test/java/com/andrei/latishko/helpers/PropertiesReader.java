package com.andrei.latishko.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertiesReader {

    private static final String DEFAULT_RESOURCE_PATH = "application.properties";

    public Properties readProperties() throws IOException {
        return readProperties(DEFAULT_RESOURCE_PATH);
    }

    public Properties readProperties(String resourcePath) throws IOException {
        FileInputStream fileInputStream = null;
        Properties properties = null;
        // todo switch to "try with resources"
        // todo learn resource closing in Java: Closable, Autocloseable
        try {
            fileInputStream = new FileInputStream(resourcePath);
            properties = new Properties();
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Objects.requireNonNull(fileInputStream).close();
        }
        return properties;
    }
}
