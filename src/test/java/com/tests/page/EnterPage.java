package com.tests.page;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Selenide.$x;

public class EnterPage {
    public void selectEnterForEmail() {
         $x("//a[@id='_loginByMail']").shouldBe(Condition.visible).click();
    }
}
