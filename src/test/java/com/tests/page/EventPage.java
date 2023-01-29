package com.tests.page;

import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class EventPage {
    public String selectEventPlace() {
        return $x("//div[@class='dd']//following::div[3]").getText();
    }

    public ElementsCollection listTopicsInEvent() {
        return $$x("//div[@class='b-post-tags']//a");
    }
}
