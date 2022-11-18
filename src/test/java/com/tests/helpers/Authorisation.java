package com.tests.helpers;

import com.codeborne.selenide.Selenide;
import com.tests.base.BaseSelenideTest;
import com.tests.page.EnterPage;
import com.tests.page.LoginPage;
import com.tests.page.MainPage;

public class Authorisation extends BaseSelenideTest {
    private static final String URL = "https://dou.ua/";

    public void correctAuthorisation(String email, String password){
        Selenide.open(URL);
        new MainPage().getLoginButton().click();
        new EnterPage().getEnterForEmail().click();
        new LoginPage().getUserTextBoxEmail()
                .setValue(email);
        new LoginPage().getUserTextBoxPassword()
                .setValue(password)
                .pressEnter();
    }
}
