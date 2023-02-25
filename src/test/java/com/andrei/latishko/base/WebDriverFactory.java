package com.andrei.latishko.base;

import com.andrei.latishko.helpers.SelenideConfig;
import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Objects;
import java.util.Set;

@ParametersAreNonnullByDefault
public class WebDriverFactory {
    public final static String PROPERTY_BROWSER = "selenium.browser";
    public final static String PROPERTY_WINDOW_SIZE = "selenium.browser.size";
    public final static String PROPERTY_SELENIDE_TIMEOUT = "selenium.selenide.timeout";

    public final static Set<String> SUPPORTED_BROWSERS = Set.of("chrome", "opera");

    public static WebDriverManager createDriverManager(SelenideConfig config) {
        Objects.requireNonNull(config);

        WebDriverManager.chromedriver().setup();
        Configuration.browser = config.getBrowser();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = config.getTimeout();

        return WebDriverManager.getInstance();
    }
}
