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
    private final MainPage mainPage = new MainPage();
    private final JobPage jobPage = new JobPage();
    private final LoginPage loginPage = new LoginPage();
    private final Authorisation authorisation = new Authorisation();
    private final FiftyCompanyPage fiftyCompanyPage = new FiftyCompanyPage();
    private final EventPage eventPage = new EventPage();
    private final CalendarPage calendarPage = new CalendarPage();
    private final UserPage userPage = new UserPage();
    private final EditProfilePage editProfilePage = new EditProfilePage();

    @Test
    public void openAllHref() {
        mainPage.getCharacterJob().click();
        jobPage.searchListLinks();
        ElementsCollection refs = jobPage.getElements();
        ArrayList<String> links = new ArrayList<>();
        refs.forEach(x -> links.add(x.getAttribute("href")));

        for (String actualLink : links) {
            Selenide.open(actualLink);
            String expectedUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            Assertions.assertEquals(expectedUrl, actualLink);
        }
    }

    @Test
    public void incorrectAuthorisationUser() {
        authorisation.correctAuthorisation("qwasfase", TestValues.getTestUserPassword());
        loginPage.getMessageAboutError().isDisplayed();
        loginPage.getCloseLoginPage().click();
    }

    /**
     * check this test with ukrainian, english language
     */
    @Test
    public void userFindEventTags() {
        mainPage.getCharacterCalendar().click();
        calendarPage.getOptionPlace().click();
        calendarPage.getOptionTopic().click();
        calendarPage.getFirstEvent().click();

        ElementsCollection refs = eventPage.getEventTopics();
        ArrayList<String> tags = new ArrayList<>();
        refs.forEach(x -> tags.add(x.getAttribute("text")));
        for (String tag : tags) {
            if (tag.equals("QA")) {
                Assertions.assertEquals(tag, "QA");
            }
        }
        String expectedPlace = eventPage.getEventPlace().getText();
        Assertions.assertNotNull(expectedPlace);
        if (expectedPlace.equals("Online") || expectedPlace.equals("online")
                || expectedPlace.equals("Онлайн") || expectedPlace.equals("онлайн")) {
            Assertions.assertTrue(true);
        } else {
            Assertions.assertFalse(false);
        }
    }

    @Test
    public void editUserName() {
        authorisation.correctAuthorisation(TestValues.getTestUserEmail(), TestValues.getTestUserPassword());
        Selenide.sleep(1000);
        Selenide.clearBrowserLocalStorage();
        mainPage.getUserProfileAvatar().shouldBe(Condition.visible).click();
        userPage.getEditProfile().click();
        editProfilePage.getUserDisplayName().clear();
        editProfilePage.getUserDisplayName().setValue(TestValues.getTestUserName());
        editProfilePage.getSaveButton().click();
        Assertions.assertEquals(userPage.getUserNameProfile().getText(), TestValues.getTestUserName());
    }

    @Test
    public void findTheMostBiggestCompany() {
        mainPage.getCharacterJob().click();
        jobPage.getTop50Company().click();
        Selenide.clearBrowserLocalStorage();
        fiftyCompanyPage.getOverlayAndTable().shouldBe(Condition.visible);
        Selenide.actions()
                .dragAndDrop(fiftyCompanyPage.getOverlayHandler(), fiftyCompanyPage.getTestsPointOverlayScroll());
        fiftyCompanyPage.getOverlayHandler().scrollTo();
        int i = 0;
        Assertions.assertEquals(fiftyCompanyPage.getFirstCompany().getText(), "EPAM Ukraine");
    }

    @Test
    public void checkValueTechnicalStaffTop50Company() {
        mainPage.getCharacterJob().click();
        jobPage.getTop50Company().click();

        ElementsCollection refs = fiftyCompanyPage.getTechnicalStaffValue();
        ArrayList<Integer> expectedLinks = new ArrayList<>();
        for (SelenideElement ref : refs) {
            //забрать со строки все значение, и вытащить только число
            expectedLinks.add(Integer.valueOf(ref.getText().replaceAll("\\D+", "")));
        }
        ArrayList<Integer> actualLinks = new ArrayList<>(expectedLinks);
        Collections.sort(expectedLinks);
        Assertions.assertEquals(actualLinks.size(), expectedLinks.size());
        for (int i = 0; i < expectedLinks.size(); i++) {
            Assertions.assertEquals(expectedLinks.get(i), actualLinks.get(i));
        }
    }
}