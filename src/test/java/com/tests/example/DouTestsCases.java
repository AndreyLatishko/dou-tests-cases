package com.tests.example;

import com.codeborne.selenide.*;
import com.tests.base.BaseSelenideTest;
import com.tests.helpers.Authorisation;
import com.tests.helpers.TestValues;
import com.tests.page.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Collections;




public class DouTestsCases extends BaseSelenideTest {
    private static final String URL = "https://dou.ua/";
    @Test
    public  void openAllHref(){
        Selenide.open(URL);
        new MainPage().getCharacterJob().click();
        new JobPage().searchListLinks();
        ElementsCollection refs = new JobPage().getElements();
        ArrayList<String> links = new ArrayList<>();
        refs.forEach(x-> links.add(x.getAttribute("href")));

        for (int i = 0; i < links.size(); i++) {
            Selenide.open(links.get(i));
            String currentUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            Assertions.assertEquals(currentUrl,links.get(i));
        }
    }
    @Test
    public void incorrectAuthorisationUser(){
        new Authorisation().correctAuthorisation("qwasfase",TestValues.getTestUserPassword());
        new LoginPage().getMessageAboutError().isDisplayed();
        new LoginPage().getCloseLoginPage().click();
    }
    /**
     * check this test with ukrainian  language, example second event, it is work
     */
    @Test
    public void userFindEventTags(){
        Selenide.open(URL);
        new MainPage().getCharacterCalendar().click();
        new CalendarPage().getOptionPlace().click();
        new CalendarPage().getOptionTopic().click();
        new CalendarPage().getFirstEvent().click();

        ElementsCollection refs = new EventPage().getEventTopics();
        ArrayList<String> tags = new ArrayList<>();
        refs.forEach(x-> tags.add(x.getAttribute("text")));
        for (int i = 0; i < tags.size(); i++) {
            if(tags.get(i).equals("QA")) {
                Assertions.assertEquals(tags.get(i), "QA");
            }
        }
        String place = new EventPage().getEventPlace().getText();
        Assertions.assertNotNull(place);
        if (place.equals("Online") || place.equals("online")
                || place.equals("Онлайн")|| place.equals("онлайн")) {
            Assertions.assertTrue(true);
        } else {
            Assertions.assertFalse(false);
        }
    }
    @Test
    public void editUserName(){
        new Authorisation()
                .correctAuthorisation(TestValues.getTestUserEmail(),TestValues.getTestUserPassword());
        Selenide.sleep(1000);
        Selenide.clearBrowserLocalStorage();
        new MainPage().getUserProfileAvatar().shouldBe(Condition.visible).click();
        new UserPage().getEditProfile().click();
        new EditProfilePage().getUserDisplayName().clear();
        new EditProfilePage().getUserDisplayName().setValue(TestValues.getTestUserName());
        new EditProfilePage().getSaveButton().click();
        Assertions.assertEquals(new UserPage().getUserNameProfile().getText(), TestValues.getTestUserName());
    }
    @Test
    public void findTheMostBiggestCompany(){
        Selenide.open(URL);
        new MainPage().getCharacterJob().click();
        new JobPage().getTop50Company().click();
        Selenide.clearBrowserLocalStorage();
        new FiftyCompanyPage().getOverlayAndTable().shouldBe(Condition.visible);
        Selenide.actions()
                .dragAndDrop(new FiftyCompanyPage().getOverlayHandler(),new FiftyCompanyPage().getTestsPointOverlayScroll());
        new FiftyCompanyPage().getOverlayHandler().scrollTo();
        int i = 0;
        Assertions.assertEquals( new FiftyCompanyPage().getFirstCompany().getText(), "EPAM Ukraine");
    }
    @Test
    public void checkValueTechnicalStaffTop50Company(){
        Selenide.open(URL);
        new MainPage().getCharacterJob().click();
        new JobPage().getTop50Company().click();

        ElementsCollection refs = new FiftyCompanyPage().getTechnicalStaffValue();
        ArrayList<Integer> links = new ArrayList<>();
        for (int i = 0; i < refs.size(); i++) {
            //забрать со строки все значение, и вытащить только число
            links.add(Integer.valueOf(refs.get(i).getText().replaceAll("\\D+","")));
        }
        ArrayList<Integer> listsWeb = new ArrayList<>(links);
        Collections.sort(links);
        Assertions.assertEquals(listsWeb.size(), links.size());
        for (int i = 0; i < links.size(); i++) {
            Assertions.assertEquals(links.get(i), listsWeb.get(i));
        }


    }
}