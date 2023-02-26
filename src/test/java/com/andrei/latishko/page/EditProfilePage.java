package com.andrei.latishko.page;


import static com.codeborne.selenide.Selenide.$x;

public class EditProfilePage {
    public void setNewName(String name) {
        $x("//input[@name='display_name']").clear();
        $x("//input[@name='display_name']").setValue(name).pressEnter();
    }
}
