package com.tests.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class CompanyPage {
    public SelenideElement getEvaluationCompany() {
        return $x("//*[@class='g-h3']");
    }
}
