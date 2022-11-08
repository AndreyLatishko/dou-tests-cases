package com.tests.example;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.tests.base.BaseSelenideTest;
import com.tests.helpers.TestValues;
import com.tests.page.JobPage;
import com.tests.page.MainPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class DouTestsCases extends BaseSelenideTest {
    private static final String URL = "https://dou.ua/";

    public ArrayList<String> searchJobsQAJunior(){
        Selenide.open(URL);
        new MainPage().getCharacterJob().click();
        new JobPage().searchListLinks();

        ElementsCollection hrefs = new JobPage().getElements();
        ArrayList<String> links = new ArrayList<>();
        hrefs.forEach(x-> links.add(x.getAttribute("href")));

        return links;
    }

    @Test
    public  void openAllHrefs(){
        ArrayList<String> links = searchJobsQAJunior();
        for (int i = 0; i < links.size(); i++) {
            String listUrl = links.get(i);
            Selenide.open(listUrl);
            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            Assertions.assertEquals(currentUrl,listUrl);
        }
    }

    @Test
    public void openAndSaveCalendarEvent(){
        Selenide.open(URL);
        $x("//a[@id='login-link']").click();
        $x("//div[@class='alt-login-method']//a[@id='_loginByMail']").click();
        $x("//div[@class='input']//input[@type ='text']")
                .setValue(TestValues.getUserEmail());
        $x("//div[@class='input']//input[@type ='password']")
                .setValue(TestValues.getUserPassword())
                .pressEnter();
        $x("//li[@class='m-last']//a").shouldBe(Condition.visible).click();
        $x("//option[@value='https://dou.ua/calendar/city/online/']").click();
        $x("//select[@name='tag']//option[@value='https://dou.ua/calendar/tags/QA/online/']").click();
        $$x("//article[@class='b-postcard ']//h2//a");

    }
}
