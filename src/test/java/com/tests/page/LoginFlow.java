package com.tests.page;

/**
 * This class implements UI login flow to be reused across tests.
 */
public class LoginFlow {
    private final MainPage mainPage = new MainPage();
    private final EnterPage enterPage = new EnterPage();
    private final LoginBorder loginBorder = new LoginBorder();

    public void authorizationEmail(String email, String password) {
        mainPage.loginButton();
        enterPage.selectEnterForEmail();
        loginBorder.inputEmail(email);
        loginBorder.inputPassword(password);
    }
}
