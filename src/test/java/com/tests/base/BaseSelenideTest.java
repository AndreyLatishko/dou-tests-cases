package com.tests.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.tests.helpers.PropertiesReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import static com.tests.base.WebDriverFactory.*;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Properties;

@ParametersAreNonnullByDefault
public class BaseSelenideTest {
    private static final String URL = "https://dou.ua/"; // todo resources (test.properties -> base.url)

    @BeforeEach
    public void setUp(){
        final Properties properties = new PropertiesReader().readProperties();
        createDriverManager(properties);
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Selenide.open(URL); // todo read from properties + validate
    }

    @AfterAll
    public static void tearDown(){
        WebDriverManager.chromedriver().quit();
    }
}
