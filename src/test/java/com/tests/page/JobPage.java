package com.tests.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class JobPage {

    public ElementsCollection getElements() {
        return $$x("//li[@class='l-vacancy']//div[@class='title']//a[@class='vt']");
    }

    public void searchListLinks() {
        $x("//option[@value='QA']").click();
        $x("//input[@class='job']").setValue("junior").pressEnter();
    }

    public SelenideElement getTop50Company() {
        return $x("//li[@class='top']//a");
    }
}
