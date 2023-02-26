package com.andrei.latishko.helpers;

import com.andrei.latishko.base.WebDriverFactory;

import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;


public class SelenideConfig {
    public final String browser;
    public final String browserSize;
    public final long timeout;

    private SelenideConfig(Properties properties) {
        Objects.requireNonNull(properties);
        validateProperties(properties);

        this.browser = properties.getProperty(WebDriverFactory.PROPERTY_BROWSER);
        this.browserSize = properties.getProperty("selenide.window.size");
        this.timeout = Long.parseLong(properties.getProperty("selenide.timeout"));
    }

    public String getBrowser() {
        return browser;
    }

    public String getBrowserSize() {
        return browserSize;
    }

    public long getTimeout() {
        return timeout;
    }

    public static SelenideConfig readConfig() throws IOException {
        final Properties properties = new PropertiesReader().readProperties("selenide.properties");
        return new SelenideConfig(properties);
    }

    private static void validateProperties(Properties properties) {
        Set.of(
                WebDriverFactory.PROPERTY_BROWSER,
                WebDriverFactory.PROPERTY_WINDOW_SIZE,
                WebDriverFactory.PROPERTY_SELENIDE_TIMEOUT
        ).forEach(requiredProperty -> {
            if (!properties.containsKey(requiredProperty)) {
                throw new IllegalArgumentException(String.format("Required property is missed: %s", requiredProperty));
            }
        });

        final String browser = properties.getProperty(WebDriverFactory.PROPERTY_BROWSER);
        if (!WebDriverFactory.SUPPORTED_BROWSERS.contains(browser)) {
            throw new IllegalArgumentException(String.format("Browser is not supported: %s", browser));
        }

        // throws IllegalArgumentException exception if number format is wrong
        Long.parseLong(properties.getProperty(WebDriverFactory.PROPERTY_SELENIDE_TIMEOUT));
    }
}
