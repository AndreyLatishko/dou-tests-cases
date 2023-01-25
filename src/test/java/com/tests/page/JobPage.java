package com.tests.page;


import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class JobPage {

    public ElementsCollection getAllVacancies() {
        return $$x("//li[@class='l-vacancy']//div[@class='title']//a[@class='vt']");
    }

    public void searchListLinks(String specialization, String jobPosition) {
        $x(String.format("//option[@value='%s']", specialization)).click();
        $x("//input[@class='job']").setValue(jobPosition).pressEnter();
    }

    public void openTop50CompanyTab() {
        $x("//li[@class='top']//a").click();
    }

    public void selectCompany(String titleCompany) {
        $x(String.format("//*[@alt='%s']", titleCompany)).click();
    }
}
