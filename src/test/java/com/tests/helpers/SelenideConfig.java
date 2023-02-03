package com.tests.helpers;

import java.io.IOException;
import java.util.Properties;

public class SelenideConfig {
    private final Properties propertiesReader = new PropertiesReader().readProperties("selenide.properties");

    public SelenideConfig() throws IOException {
    }

    public String browser = propertiesReader.getProperty("selenide.browser");
    public String browserSize = propertiesReader.getProperty("selenide.window.size");
    public String timeout = propertiesReader.getProperty("selenide.timeout");
}
