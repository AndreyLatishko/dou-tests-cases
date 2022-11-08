package com.tests.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public SelenideElement getCharacterJob() {
        return $x("//a[@href='https://jobs.dou.ua/']");
    }
}
