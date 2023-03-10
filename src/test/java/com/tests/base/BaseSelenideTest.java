package com.tests.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;

public class BaseSelenideTest {
    private static final String URL = "https://dou.ua/";
    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 40;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Selenide.open(URL);
    }

    @AfterAll
    public  static void tearDown(){
        WebDriverManager.chromedriver().quit();
    }
}
