package com.tests.helpers;

import com.tests.page.EnterPage;
import com.tests.page.LoginPage;
import com.tests.page.MainPage;

public class Authorisation {
    private final MainPage mainPage = new MainPage();
    private final EnterPage enterPage = new EnterPage();
    private final LoginPage loginPage = new LoginPage();

    public void authorisationEmail(String email, String password) {
        mainPage.getLoginButton().click();
        enterPage.getEnterForEmail().click();
        loginPage.getUserTextBoxEmail()
                .setValue(email);
        loginPage.getUserTextBoxPassword()
                .setValue(password)
                .pressEnter();
    }
}
