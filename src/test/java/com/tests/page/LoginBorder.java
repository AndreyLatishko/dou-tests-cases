package com.tests.page;

import static com.codeborne.selenide.Selenide.$x;

public class LoginBorder {
    public void inputEmail(String email) {
         $x("//div[@class='input']//input[@type ='text']").setValue(email);
    }

    public void inputPassword(String password) {
         $x("//div[@class='input']//input[@type ='password']").setValue(password).pressEnter();
    }

    public void alertForError() {
         $x("//div[@id='wrong-password-message']").isDisplayed();
    }

    public void closeLoginBorder() {
         $x("//div[@class='close cancel']").click();
    }
}
