package com.andrei.latishko.page;

import static com.codeborne.selenide.Selenide.$x;

public class CompanyPage {
    public int getScoreCompany() {
        String scoreText = $x("//*[@class='g-h3']")
                .getText()
                .replaceAll("\\D+", "");
        return Integer.parseInt(scoreText) / 1000;
    }
}
