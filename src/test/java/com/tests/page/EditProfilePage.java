package com.tests.page;

import com.codeborne.selenide.SelenideElement;
import com.tests.base.BaseSelenideTest;

import static com.codeborne.selenide.Selenide.$x;

public class EditProfilePage extends BaseSelenideTest {
    public SelenideElement getUserDisplayName(){
        return $x("//input[@name='display_name']");
    }
    public SelenideElement getSaveButton(){
        return $x("//button[@id='btnSubmit']");
    }
}
