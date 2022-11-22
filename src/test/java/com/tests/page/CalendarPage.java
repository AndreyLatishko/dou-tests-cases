package com.tests.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CalendarPage {
    public SelenideElement getOptionPlace() {
        return $x("//select[@name='city']//option//following::option[1]");
    }

    public SelenideElement getOptionTopic(String topic) {
        return $x(String.format("//select[@name='tag']//option[text()='%s']", topic));
    }

    public SelenideElement getFirstEvent() {
        return $x("//following::article[1]//h2//a");
    }
}
