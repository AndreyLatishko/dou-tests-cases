package com.andrei.latishko.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class FiftyCompanyPage {
    public ArrayList<Integer> getTechnicalStaffValue() {
        ElementsCollection refs =
                $$x("//tr[contains(@class,'tr')]//td[@class='col-3']//span[@class='staffTech-value']");
        ArrayList<Integer> actualLinks = new ArrayList<>();
        for (SelenideElement ref : refs) {
            actualLinks.add(Integer.valueOf(ref.getText().replaceAll("\\D+", "")));
        }
        return actualLinks;
    }

    public String getFirstCompany() {
        return $x("//div[@class='dou-chart']//tr[@class='tr-1']" +
                "//td[@class='col-1']//div[@class='company-name-block']//a").getText();
    }

    public void getOverlayAndTable() {
        $x("//div[@id='chart-top50-table']").shouldBe(Condition.visible);
        Selenide.executeJavaScript(
                "document.querySelector" +
                        "(\"#period-slider > g >g.handler\").style.transform = 'translate(1229px,0)';"
        );
    }

    public ArrayList<Integer> sortArray(ArrayList<Integer> list){
        ArrayList<Integer> expectedLinks = new ArrayList<>(list);
        Collections.sort(expectedLinks);
        return expectedLinks;
    }


}
