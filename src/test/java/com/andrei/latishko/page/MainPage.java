package com.andrei.latishko.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class MainPage {


    public void openJobTab() {
        $x("//a[text()='Робота']").click();
    }

    public void openCalendarTab() {
        $x("//a[text()='Календар']").click();
    }

    public void loginButton() {
        $x("//a[@id='login-link']").click();
    }

    public void openUserProfile() {
        $("a.min-profile").shouldBe(Condition.visible).click();
    }

}
