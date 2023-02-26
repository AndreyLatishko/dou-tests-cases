package com.andrei.latishko.page;

import static com.codeborne.selenide.Selenide.$x;

public class CalendarPage {
    public void selectPlace(String place) {
        $x(String.format("//option[text()='%s']", place)).click();
    }

    public void selectTopic(String topic) {
        $x(String.format("//select[@name='tag']//option[text()='%s']", topic)).click();
    }

    public void openFirstEvent() {
        $x("//following::article[1]//h2//a").click();
    }
}
