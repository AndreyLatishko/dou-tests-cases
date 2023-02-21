package com.andrei.latishko.tests;

import com.andrei.latishko.base.BaseSelenideTest;
import com.andrei.latishko.helpers.PropertiesReader;
import com.andrei.latishko.helpers.TestValues;
import com.andrei.latishko.page.*;
import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Description;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


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
    @Description("This test demonstrates that a user cannot log in with an incorrect email.")
    public void incorrectAuthorisationUser() {
        loginFlow.authorizeUserWithEmail("wrong@gmail.com", properties.getProperty("user.password"));
        assertTrue(loginBorder.isAlertAppeared(), "Error, alert did not appear");
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

        ArrayList<String> tags = eventPage.getEventTopics();
        for (String tag : tags) {
                assertEquals(tag, TestValues.SPECIALIZATION);
        }

        String expectedPlace = eventPage.selectEventPlace();
        assertNotNull(expectedPlace);
        assertTrue(Set.of("Online", "online", "Онлайн", "онлайн").contains(expectedPlace));
    }

    @Test
    @Description("Checking the new user name after a change.")
    public void editUserName() {
        String email = properties.getProperty("user.login");
        String password = properties.getProperty("user.password");
        loginFlow.authorizeUserWithEmail(email,password);
        mainPage.openUserProfile();
        userPage.openEditProfile();
        editProfilePage.setNewName(TestValues.TEST_USER_NAME);
        assertEquals(userPage.getUserName(), TestValues.TEST_USER_NAME);
    }

    @Test
    @Description("Checking the largest company for January 2022.")
    public void findTheMostBiggestCompany() {
        mainPage.openJobTab();
        jobPage.openTop50CompanyTab();
        fiftyCompanyPage.getOverlayAndTable();
        assertEquals(fiftyCompanyPage.getFirstCompany(), "EPAM Ukraine");
    }

    @Test
    @Description("Check if the company is correctly sorted by the number of technicians.")
    public void checkValueTechnicalStaffTop50Company() {
        mainPage.openJobTab();
        jobPage.openTop50CompanyTab();

        ArrayList<Integer> actualLinks= fiftyCompanyPage.getTechnicalStaffValue();
        ArrayList<Integer> expectedLinks = fiftyCompanyPage.sortArray(actualLinks);

        assertIterableEquals(expectedLinks,actualLinks);
    }

    @Test
    @Description("Checking that the company's valuation is within acceptable numerical ranges.")
    public void evaluationProvideCompany(){
        mainPage.openJobTab();
        jobPage.selectCompany(TestValues.COMPANY_TITLE);
        int score = companyPage.getScoreCompany();
        assertTrue(score >= 0 && score <= 100);
    }
}
