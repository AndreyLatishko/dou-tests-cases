package com.tests.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.tests.base.BaseSelenideTest;

import static com.codeborne.selenide.Selenide.*;

public class FiftyCompanyPage extends BaseSelenideTest {

    public SelenideElement getTestsPointOverlayScroll(){
      //  return  $x("//*[text()='Січ 22']/..");
        return  $x("//*[text()='Січень 2022']/ancestor::*[1]");
    }
    public ElementsCollection getTechnicalStaffValue(){
        return $$x("//tr[contains(@class,'tr')]//td[@class='col-3']//span[@class='staffTech-value']");
    }

    public SelenideElement getFirstCompany(){
        return $x("//div[@class='dou-chart']//tr[@class='tr-1']//td[@class='col-1']//div[@class='company-name-block']//a");
    }

    public SelenideElement getOverlayAndTable() {
        return $x("//div[@id='chart-top50-table']");
    }

    public SelenideElement getOverlay() {
        return $x("//div[@id='slider-wrapper']");
    }
    public SelenideElement getOverlayHandler(){
        return $x("//*[@class='handler']");
    }
}
