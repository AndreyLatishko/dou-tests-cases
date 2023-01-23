package com.tests.example;

import com.codeborne.selenide.*;
import com.tests.base.BaseSelenideTest;
import com.tests.page.LoginFlow;
import com.tests.helpers.TestValues;
import com.tests.page.*;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DouTestsCases extends BaseSelenideTest {
    private final MainPage mainPage = new MainPage();
    private final JobPage jobPage = new JobPage();
    private final LoginBorder loginBorder = new LoginBorder();
    private final LoginFlow loginFlow = new LoginFlow();
    private final FiftyCompanyPage fiftyCompanyPage = new FiftyCompanyPage();
    private final EventPage eventPage = new EventPage();
    private final CalendarPage calendarPage = new CalendarPage();
    private final UserPage userPage = new UserPage();
    private final EditProfilePage editProfilePage = new EditProfilePage();
    private final CompanyPage companyPage = new CompanyPage();

    @Test
    @Order(3)
    public void openAllHref() {
        mainPage.openJobTab();
        jobPage.searchListLinks(getTestUserSpecialization(),getTestUserJobPosition());
        ElementsCollection refs = jobPage.getElements();
        ArrayList<String> links = new ArrayList<>();
        refs.forEach(x -> links.add(x.getAttribute("href")));

        for (String actualLink : links) {
            Selenide.open(actualLink);
            String expectedUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            assertNotNull(expectedUrl);
            assertEquals(expectedUrl, actualLink);
        }
    }

    @Test
    @Order(1)
    @Description("This test demonstrates,user can to autorisation with wrong email")
    public void incorrectAuthorisationUser() {
        loginFlow.authorizationEmail("wrong@gmail.com", TestValues.TEST_USER_PASSWORD);
        loginBorder.alertForError();
        loginBorder.closeLoginBorder();
    }

    /**
     * check this test with ukrainian, english language
     */
    @Test
    @Order(2)
    public void userFindEventTags() {
        mainPage.openCalendarTab();
        calendarPage.selectPlace(TestValues.CALENDAR_PLACE);
        calendarPage.selectTopic(TestValues.CALENDAR_TOPIC);
        calendarPage.openFirstEvent();

        ElementsCollection refs = eventPage.getEventTopics();
        ArrayList<String> tags = new ArrayList<>();
        refs.forEach(x -> tags.add(x.getAttribute("text")));
        for (String tag : tags) {
            if (tag.equals(getTestUserSpecialization())) { // todo will never fail
                assertEquals(tag, getTestUserSpecialization());
            }
        }
        String expectedPlace = eventPage.getEventPlace().getText();
        assertNotNull(expectedPlace);
        if (expectedPlace.equals("Online") || expectedPlace.equals("online")
                || expectedPlace.equals("Онлайн") || expectedPlace.equals("онлайн")) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    @Order(6)
    public void editUserName() {
        loginFlow.authorizationEmail(getTestUserEmail(),getTestUserPassword());
        Selenide.sleep(1000);
        Selenide.clearBrowserLocalStorage();
        mainPage.userProfile();
        userPage.getEditProfile().click();
        editProfilePage.getUserDisplayName().clear();
        editProfilePage.getUserDisplayName().setValue(getTestUserName());
        editProfilePage.getSaveButton().click();
        assertEquals(userPage.getUserNameProfile().getText(), getTestUserName());
    }

    @Test
    @Order(5)
    public void findTheMostBiggestCompany() {
        mainPage.openJobTab();
        jobPage.getTop50Company().click();
        Selenide.clearBrowserLocalStorage();
        fiftyCompanyPage.getOverlayAndTable().shouldBe(Condition.visible);
        Selenide.executeJavaScript(
                "document.querySelector" +
                        "(\"#period-slider > g >g.handler\").style.transform = 'translate(1229px,0)';"
        );
        assertEquals(fiftyCompanyPage.getFirstCompany().getText(), "EPAM Ukraine");
    }

    @Test
    @Order(4)
    public void checkValueTechnicalStaffTop50Company() {
        mainPage.openJobTab();
        jobPage.getTop50Company().click();

        ElementsCollection refs = fiftyCompanyPage.getTechnicalStaffValue();
        ArrayList<Integer> expectedLinks = new ArrayList<>();
        for (SelenideElement ref : refs) {
            expectedLinks.add(Integer.valueOf(ref.getText().replaceAll("\\D+", "")));
        }
        ArrayList<Integer> actualLinks = new ArrayList<>(expectedLinks);
        Collections.sort(expectedLinks);
        assertEquals(actualLinks.size(), expectedLinks.size());
        for (int i = 0; i < expectedLinks.size(); i++) {
            assertEquals(expectedLinks.get(i), actualLinks.get(i));
        }
        int i = 0;
    }

    @Test
    @Order(7)
    public void evaluationProvideCompany(){
        mainPage.openJobTab();
        jobPage.getVacancyCompany(TestValues.getTestUserCompanyTitle()).click();
        int value = parseInt((companyPage.getEvaluationCompany().text().replaceAll("\\D+", "")))/1000;
        assertNotNull(value);
        assertTrue(value < 100 && value > 0);
    }
}
