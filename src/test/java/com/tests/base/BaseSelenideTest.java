package com.tests.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.tests.helpers.PropertiesReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

import javax.annotation.ParametersAreNonnullByDefault;
import java.io.IOException;
import java.util.Properties;

import static com.tests.base.WebDriverFactory.createDriverManager;

@ParametersAreNonnullByDefault
public class BaseSelenideTest {

    @BeforeEach
    public void setUp() throws IOException {
        final Properties properties = new PropertiesReader().readProperties();
        createDriverManager(properties);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        String url = properties.getProperty("basic.url");
        if (!url.isEmpty()) {
            Selenide.open(url);
        }
    }

    @AfterAll
    public static void tearDown() {
        WebDriverManager.chromedriver().quit();
    }
}
