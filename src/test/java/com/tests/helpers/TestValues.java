package com.tests.helpers;

public class TestValues { // TODO just use public constants
    private static final String TEST_USER_EMAIL = "asdasfasfas@gmail.com";
    private static final String TEST_USER_PASSWORD = "asfasf";
    private static final String TEST_USER_NAME = "Андрій Латишко";

    // todo user prefix
    private static final String TEST_USER_SPECIALIZATION = "QA";
    private static final String TEST_USER_JOBPOSIITION = "junior";
    private static final String TEST_USER_CALENDARTOPIC = "QA";
    private static final String TEST_USER_COMPANY_TITLE = "Parimatch Tech";

    public static String getTestUserCalendarTopic() {return TEST_USER_CALENDARTOPIC;}

    public static String getTestUserEmail() {
        return TEST_USER_EMAIL;
    }

    public static String getTestUserPassword() {
        return TEST_USER_PASSWORD;
    }

    public static String getTestUserName() {return TEST_USER_NAME;}

    public static String getTestUserSpecialization() {return TEST_USER_SPECIALIZATION;}

    public static String getTestUserJobPosition() {return TEST_USER_JOBPOSIITION;}

    public static String getTestUserCompanyTitle() {
        return TEST_USER_COMPANY_TITLE;
    }
}
