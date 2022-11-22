package com.tests.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    public SelenideElement getCharacterJob() {
        return $x("//a[text()='Робота']");
    }

    public SelenideElement getCharacterCalendar() {
        return $x("//li[@class='m-last']//a");
    }

    public SelenideElement getLoginButton() {
        return $x("//a[@id='login-link']");
    }

    public SelenideElement getUserProfileAvatar() {
        return $("a.min-profile");
    }

}
