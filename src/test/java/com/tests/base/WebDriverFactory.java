package com.tests.base;

import com.codeborne.selenide.Configuration;
import com.tests.helpers.SelenideConfig;
import io.github.bonigarcia.wdm.WebDriverManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

@ParametersAreNonnullByDefault
public class WebDriverFactory {
    private  final  static SelenideConfig selenideConfig;

    static {
        try {
            selenideConfig = new SelenideConfig();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final static String PROPERTY_BROWSER = selenideConfig.browser;
    private final static String PROPERTY_WINDOW_SIZE = selenideConfig.browserSize;
    private final static String PROPERTY_SELENIDE_TIMEOUT = selenideConfig.timeout;

    private final static Set<String> SUPPORTED_BROWSERS = Set.of("chrome", "opera");

    // todo test selenide.properties
    // todo if selenide.properties is not an option -> switch to separate Configuration class

    public static WebDriverManager createDriverManager(Properties properties) {
        Objects.requireNonNull(properties);
        validateProperties(properties);

        WebDriverManager.chromedriver().setup();
        Configuration.browser = properties.getProperty(PROPERTY_BROWSER);
        Configuration.browserSize = properties.getProperty(PROPERTY_WINDOW_SIZE);
        Configuration.timeout = Long.parseLong(properties.getProperty(PROPERTY_SELENIDE_TIMEOUT));

        return WebDriverManager.getInstance();
    }
    private static void validateProperties(Properties properties) { // todo move validation of properties to single place
        Set.of(
                PROPERTY_BROWSER,
                PROPERTY_WINDOW_SIZE,
                PROPERTY_SELENIDE_TIMEOUT
        ).forEach(requiredProperty -> {
            if (!properties.containsKey(requiredProperty)) {
                throw new IllegalArgumentException(String.format("Required property is missed: %s", requiredProperty));
            }
        });

        final String browser = properties.getProperty(PROPERTY_BROWSER);
        if (!SUPPORTED_BROWSERS.contains(browser)) {
            throw new IllegalArgumentException(String.format("Browser is not supported: %s", browser));
        }

        // throws IllegalArgumentException exception if number format is wrong
        Long.parseLong(properties.getProperty(PROPERTY_SELENIDE_TIMEOUT));
    }
}
