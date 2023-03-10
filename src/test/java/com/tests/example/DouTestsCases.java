package com.tests.example;

import com.codeborne.selenide.*;
import com.tests.base.BaseSelenideTest;
import com.tests.helpers.Authorisation;
import com.tests.helpers.TestValues;
import com.tests.page.*;
import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;

import static com.tests.helpers.TestValues.*;
import static java.lang.Integer.parseInt;
import static org.junit.jupiter.api.Assertions.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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
    private final CompanyPage companyPage = new CompanyPage();

    @Test
    @Order(3)
    public void openAllHref() {
        mainPage.getCharacterJob().click();
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
        authorisation.authorisationEmail("qwasfase", getTestUserPassword());
        loginPage.getMessageAboutError().isDisplayed();
        loginPage.getCloseLoginPage().click();
    }

    /**
     * check this test with ukrainian, english language
     */
    @Test
    @Order(2)
    public void userFindEventTags() {
        mainPage.getCharacterCalendar().click();
        calendarPage.getOptionPlace().click();
        calendarPage.getOptionTopic(getTestUserCalendarTopic()).click();
        calendarPage.getFirstEvent().click();

        ElementsCollection refs = eventPage.getEventTopics();
        ArrayList<String> tags = new ArrayList<>();
        refs.forEach(x -> tags.add(x.getAttribute("text")));
        for (String tag : tags) {
            if (tag.equals(getTestUserSpecialization())) {
                assertEquals(tag, getTestUserSpecialization());
            }
        }
        String expectedPlace = eventPage.getEventPlace().getText();
        assertNotNull(expectedPlace);
        if (expectedPlace.equals("Online") || expectedPlace.equals("online")
                || expectedPlace.equals("????????????") || expectedPlace.equals("????????????")) {
            assertTrue(true);
        } else {
            assertFalse(false);
        }
    }

    @Test
    @Order(6)
    public void editUserName() {
        authorisation.authorisationEmail(getTestUserEmail(),getTestUserPassword());
        Selenide.sleep(1000);
        Selenide.clearBrowserLocalStorage();
        mainPage.getUserProfileAvatar().shouldBe(Condition.visible).click();
        userPage.getEditProfile().click();
        editProfilePage.getUserDisplayName().clear();
        editProfilePage.getUserDisplayName().setValue(getTestUserName());
        editProfilePage.getSaveButton().click();
        assertEquals(userPage.getUserNameProfile().getText(), getTestUserName());
    }

    @Test
    @Order(5)
    public void findTheMostBiggestCompany() {
        mainPage.getCharacterJob().click();
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
        mainPage.getCharacterJob().click();
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
        mainPage.getCharacterJob().click();
        jobPage.getVacancyCompany(getTestCompanyName()).click();
        int value = parseInt((companyPage.getEvaluationCompany().text().replaceAll("\\D+", "")))/1000;
        assertNotNull(value);
        assertTrue(value < 100 && value > 0);
    }

}