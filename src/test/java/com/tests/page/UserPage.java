package com.tests.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class UserPage {
    public SelenideElement getEditProfile() {
        return $x("//div[@class='wrap edit-content-link']//a[text()='Редагувати профіль']");
    }

    public SelenideElement getUserNameProfile() {
        return $x("//div[@class='head']//h1");
    }
}
