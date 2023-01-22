package com.tests.page;

/**
 * This class implements UI login flow to be reused across tests.
 */
public class LoginFlow {
    private final MainPage mainPage = new MainPage();
    private final EnterPage enterPage = new EnterPage();
    private final LoginPage loginPage = new LoginPage();

    public void authorizationEmail(String email, String password) {
        mainPage.getLoginButton().click();
        enterPage.getEnterForEmail().click();
        loginPage.getUserTextBoxEmail()
                .setValue(email);
        loginPage.getUserTextBoxPassword()
                .setValue(password)
                .pressEnter();
    }
}
