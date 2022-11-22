package com.tests.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class EnterPage {
    public SelenideElement getEnterForEmail() {
        return $x("//div[@class='alt-login-method']//a[@id='_loginByMail']");
    }
}
