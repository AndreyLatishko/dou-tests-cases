package com.tests.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.tests.base.BaseSelenideTest;

import static com.codeborne.selenide.Selenide.*;

public class FiftyCompanyPage extends BaseSelenideTest {
    public ElementsCollection getTechnicalStaffValue(){
        return $$x("//tr[contains(@class,'tr')]//td[@class='col-3']//span[@class='staffTech-value']");
    }

    public SelenideElement getFirstCompany(){
        return $x("//div[@class='dou-chart']//tr[@class='tr-1']//td[@class='col-1']//div[@class='company-name-block']//a");
    }

    public SelenideElement getOverlayAndTable() {
        return $x("//div[@id='chart-top50-table']");
    }

}
