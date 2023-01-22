package com.tests.base;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import static com.tests.base.WebDriverFactory.*;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class BaseSelenideTest {
    private static final String URL = "https://dou.ua/";
    @BeforeEach
    public void setUp(){
        setBrowser("chrome");
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Selenide.open(URL);
    }

    @AfterAll
    public  static void tearDown(){
        WebDriverManager.chromedriver().quit();
    }
}
