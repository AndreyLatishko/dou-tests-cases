package com.tests.page;

import com.codeborne.selenide.ElementsCollection;
import com.tests.base.BaseSelenideTest;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class JobPage extends BaseSelenideTest {

    public ElementsCollection getElements(){
        return $$x("//li[@class='l-vacancy']//div[@class='title']//a[@class='vt']");
    }

    public void searchListLinks(){
        $x("//option[@value='QA']").click();
        $x("//input[@class='job']").setValue("junior").pressEnter();
    }
}
