package com.tests.page;

import com.codeborne.selenide.SelenideElement;
import com.tests.base.BaseSelenideTest;

import static com.codeborne.selenide.Selenide.$x;

public class EnterPage extends BaseSelenideTest {
    public SelenideElement getEnterForEmail (){
        return  $x("//div[@class='alt-login-method']//a[@id='_loginByMail']");
    }
}
