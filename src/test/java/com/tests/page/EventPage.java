package com.tests.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.tests.base.BaseSelenideTest;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class EventPage extends BaseSelenideTest {
    public SelenideElement getEventPlace(){
        return $x("//div[@class='dd']//following::div[3]");
    }
    public ElementsCollection getEventTopics(){
        return $$x("//div[@class='b-post-tags']//a");
    }
}
