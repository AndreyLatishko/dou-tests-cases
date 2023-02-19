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
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Description;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
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

    // todo add everywhere descriptions

    // todo avoid selenide code in tests only buisness logic

    // todo get rid of all non english comments + code should be self-documented

    @Test
    @Order(3) // todo please remove order
    public void openAllHref() {
        mainPage.openJobTab();
        jobPage.searchForJobs(TestValues.SPECIALIZATION,TestValues.JOB_POSITION);
        //вытащили все доступные вакансии по предыдущим условиям
        ElementsCollection jobElements = jobPage.getAllVacancies();
        ArrayList<String> jobLinks = new ArrayList<>();
        // из предыдущего списка вытащили информацию которая содержит ссылку на вакансию
        // todo read https://selenide.org/2022/01/10/selenide-6.2.0/ "Replaced $$.iterator() by $$.asDynamicIterable() and $$.asFixedIterable()"
        jobElements.asFixedIterable().forEach(x -> jobLinks.add(x.getAttribute("href")));
        //сравниваем список линков из arraylist и те ссылки которые мы открываем через цикл
        for (String actualLink : jobLinks) {
            Selenide.open(actualLink);
            final String expectedUrl = WebDriverRunner.getWebDriver().getCurrentUrl();
            assertNotNull(expectedUrl);
            assertEquals(expectedUrl, actualLink);
        }
        // todo change verification, compare job title in the list to the job title on job page
    }

    @Test
    @Order(1)
    @Description("This test demonstrates,user can to autorisation with wrong email")
    public void incorrectAuthorisationUser() {
        loginFlow.authorizeUserWithEmail("wrong@gmail.com", properties.getProperty("user.password"));
        // todo method do nothing, return and check the value
        loginBorder.isAlertAppered();
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

        ElementsCollection refs = eventPage.listTopicsInEvent();
        // todo this looks like part of listTopicsInEvent() method (better call getEventTopics)
        //достали список тем с ивента
        ArrayList<String> tags = new ArrayList<>();
        // создали пустой список
        refs.forEach(x -> tags.add(x.getAttribute("text")));
        // прошлись по всем темам и  сохранили в пустой список
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
    @Order(6)
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
    @Order(5)
    public void findTheMostBiggestCompany() {
        mainPage.openJobTab();
        jobPage.openTop50CompanyTab();
        Selenide.clearBrowserLocalStorage();
        fiftyCompanyPage.getOverlayAndTable();
        Selenide.executeJavaScript(
                "document.querySelector" +
                        "(\"#period-slider > g >g.handler\").style.transform = 'translate(1229px,0)';"
        );
        assertEquals(fiftyCompanyPage.getFirstCompany(), "EPAM Ukraine");
    }

    @Test
    @Order(4)
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
    @Order(7)
    public void evaluationProvideCompany(){
        mainPage.openJobTab();
        jobPage.selectCompany(TestValues.COMPANY_TITLE);
        int value = Integer.parseInt((companyPage.getScoreCompany().replaceAll("\\D+", "")))/1000;
        assertTrue(value < 100 && value > 0);
    }
}
