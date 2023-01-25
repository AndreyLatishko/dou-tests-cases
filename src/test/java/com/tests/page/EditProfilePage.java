package com.tests.page;


import static com.codeborne.selenide.Selenide.$x;

public class EditProfilePage {
    public void writeNewNameUser(String name) {
        $x("//input[@name='display_name']").clear();
        $x("//input[@name='display_name']").setValue(name);
    }

    public void getSaveButton() {
        $x("//button[@id='btnSubmit']").click();
    }
}
