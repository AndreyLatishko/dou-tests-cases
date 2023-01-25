package com.tests.page;

import static com.codeborne.selenide.Selenide.$x;

public class CompanyPage {
    public void getScoreCompany() {
         $x("//*[@class='g-h3']");
    }
}
