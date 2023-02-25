package com.andrei.latishko.base;

import com.andrei.latishko.helpers.PropertiesReader;
import com.andrei.latishko.helpers.SelenideConfig;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.Properties;

@ParametersAreNonnullByDefault
public class BaseSelenideTest {

    @BeforeEach
    public void setUp() throws IOException {
        final SelenideConfig config = SelenideConfig.readConfig();
        final Properties properties = new PropertiesReader().readProperties();
        WebDriverFactory.createDriverManager(config);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());

        final String url = properties.getProperty("basic.url");
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("The 'basic.url' property is empty in the configuration file.");
        } else {
            Selenide.open(url);
        }
    }

    @AfterAll
    public static void tearDown() {
        WebDriverManager.chromedriver().quit();
    }
}
