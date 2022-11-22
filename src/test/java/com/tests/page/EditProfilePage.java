package com.tests.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class EditProfilePage {
    public SelenideElement getUserDisplayName() {
        return $x("//input[@name='display_name']");
    }

    public SelenideElement getSaveButton() {
        return $x("//button[@id='btnSubmit']");
    }
}
