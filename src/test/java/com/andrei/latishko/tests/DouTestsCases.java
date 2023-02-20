package com.andrei.latishko.tests;

import com.andrei.latishko.base.BaseSelenideTest;
import com.andrei.latishko.helpers.PropertiesReader;
import com.andrei.latishko.helpers.TestValues;
import com.andrei.latishko.page.CalendarPage;
import com.andrei.latishko.page.CompanyPage;
import com.andrei.latishko.page.EditProfilePage;
import com.andrei.latishko.page.EventPage;
import com.andrei.latishko.page.FiftyCompanyPage;
import com.andrei.latishko.page.JobPage;
import com.andrei.latishko.page.LoginBorder;
import com.andrei.latishko.page.LoginFlow;
import com.andrei.latishko.page.MainPage;
import com.andrei.latishko.page.UserPage;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Description;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


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
    private final Properties properties;

    public DouTestsCases() {
        try {
            this.properties = new PropertiesReader().readProperties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // todo avoid selenide code in tests only business logic

    @Test
    @Description("Check the functionality of the link to the job.")
    public void openAllHref() {
        mainPage.openJobTab();
        jobPage.searchForJobs(TestValues.SPECIALIZATION,TestValues.JOB_POSITION);
        ElementsCollection jobElements = jobPage.getAllVacancies();
        ArrayList<String> jobLinks = jobPage.getAllLinks(jobElements);

        for (String actualLink : jobLinks) {
            final String expectedUrl = jobPage.titleJob(actualLink);
            assertNotNull(expectedUrl);
            assertEquals(expectedUrl, actualLink);
        }
    }

    @Test
    @Description("This test demonstrates,user can to authorisation with wrong email.")
    public void incorrectAuthorisationUser() {
        loginFlow.authorizeUserWithEmail("wrong@gmail.com", properties.getProperty("user.password"));
        // todo method do nothing, return and check the value
        loginBorder.isAlertAppered();
        loginBorder.closeLoginBorder();
    }

    @Test
    @Description("Check the topic and location of the event with the search." +
            "Check this test value with ukrainian, english language.")
    public void userFindEventTags() {
        mainPage.openCalendarTab();
        calendarPage.selectPlace(TestValues.CALENDAR_PLACE);
        calendarPage.selectTopic(TestValues.CALENDAR_TOPIC);
        calendarPage.openFirstEvent();

        ElementsCollection refs = eventPage.listTopicsInEvent();
        // todo this looks like part of listTopicsInEvent() method (better call getEventTopics)
        ArrayList<String> tags = new ArrayList<>();
        refs.forEach(x -> tags.add(x.getAttribute("text")));
        for (String tag : tags) {
                assertEquals(tag, TestValues.SPECIALIZATION);
        }

        String expectedPlace = eventPage.selectEventPlace();
        assertNotNull(expectedPlace);
        Set.of(
                "Online",
                "online",
                "Онлайн",
                "онлайн"
        ).forEach(x -> assertEquals(expectedPlace, x));
    }

    @Test
    @Description("Checking the new user name after a change.")
    public void editUserName() {
        // todo read properties in a separate line
        loginFlow.authorizeUserWithEmail(properties.getProperty("user.login"),properties.getProperty("user.password"));
        Selenide.sleep(1000); // todo wait for element instead of sleep
        Selenide.clearBrowserLocalStorage();
        mainPage.openUserProfile();
        userPage.openEditProfile();
        editProfilePage.setNewName(TestValues.TEST_USER_NAME);
        editProfilePage.clickSaveButton();
        assertEquals(userPage.getUserName(), TestValues.TEST_USER_NAME);
    }

    @Test
    @Description("Checking the largest company for January 2022.")
    public void findTheMostBiggestCompany() {
        mainPage.openJobTab();
        jobPage.openTop50CompanyTab();
        //  Selenide.clearBrowserLocalStorage();
        fiftyCompanyPage.getOverlayAndTable();
        assertEquals(fiftyCompanyPage.getFirstCompany(), "EPAM Ukraine");
    }

    @Test
    @Description("Check if the company is correctly sorted by the number of technicians.")
    public void checkValueTechnicalStaffTop50Company() {
        mainPage.openJobTab();
        jobPage.openTop50CompanyTab();

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
    }

    @Test
    @Description("Checking that the company's valuation is within acceptable numerical ranges.")
    public void evaluationProvideCompany(){
        mainPage.openJobTab();
        jobPage.selectCompany(TestValues.COMPANY_TITLE);
        int value = Integer.parseInt((companyPage.getScoreCompany().replaceAll("\\D+", "")))/1000;
        assertTrue(value < 100 && value > 0);
    }
}
