package com.andrei.latishko.page;

import static com.codeborne.selenide.Selenide.$x;

public class CompanyPage {
    public String getScoreCompany() {
        return  $x("//*[@class='g-h3']").getText();
    }
}
