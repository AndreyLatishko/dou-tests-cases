package com.andrei.latishko.helpers;

import java.io.IOException;
import java.util.Properties;

public class SelenideConfig {
    public final String browser;
    public final String browserSize;
    public final String timeout;

    private SelenideConfig(Properties properties) {
        this.browser = properties.getProperty("selenide.browser");
        this.browserSize = properties.getProperty("selenide.window.size");
        this.timeout = properties.getProperty("selenide.timeout");
    }

    public static SelenideConfig readConfig() throws IOException {
        final Properties properties = new PropertiesReader().readProperties("selenide.properties");
        return new SelenideConfig(properties);
    }
}
