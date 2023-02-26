package com.andrei.latishko.page;

import com.codeborne.selenide.ElementsCollection;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class EventPage {
    public String selectEventPlace() {
        return $x("//div[@class='dd']//following::div[3]").getText();
    }

    public ArrayList<String> getEventTopics() {
        ElementsCollection refs = $$x("//div[@class='b-post-tags']//a");
        ArrayList<String> tags = new ArrayList<>();
        refs.asFixedIterable().forEach(x -> tags.add(x.getAttribute("text")));
        return tags;
    }
}
