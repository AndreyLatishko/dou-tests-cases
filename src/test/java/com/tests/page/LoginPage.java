package com.tests.page;

import com.codeborne.selenide.SelenideElement;
import com.tests.base.BaseSelenideTest;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BaseSelenideTest {
    public SelenideElement getUserTextBoxEmail(){
        return  $x("//div[@class='input']//input[@type ='text']");
    }
    public SelenideElement getUserTextBoxPassword(){
        return  $x("//div[@class='input']//input[@type ='password']");
    }
    public SelenideElement getMessageAboutError(){
        return  $x("//div[@id='wrong-password-message']");
    }
    public SelenideElement getCloseLoginPage(){
        return  $x("//div[@class='close cancel']");
    }
}
