package com.tests.page;


import static com.codeborne.selenide.Selenide.$x;

public class UserPage {
    public void openEditProfile() {
        $x("//div[@class='wrap edit-content-link']//a[text()='Редагувати профіль']").click();
    }

    public String checkUserName() {
        return $x("//div[@class='head']//h1").text();
    }
}
