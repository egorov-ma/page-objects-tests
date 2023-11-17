package ru.egorovma.tests;

import org.junit.jupiter.api.Test;
import ru.egorovma.pages.RegistrationPage;

import static ru.egorovma.data.TestDataForStudentRegistrationForm.*;

public class ToolsQaTest extends TestBase {
    private final static String CSS_PROPERTY_NAME = "border-color";
    private final static String CSS_EXPECTED_VALUE = "rgb(220, 53, 69)";
    private final static String TOOLS_QA_URL = "/automation-practice-form";

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void studentFullRegistrationForm() {
        registrationPage.openPage(TOOLS_QA_URL)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setEmail(EMAIL)
                .setGender(GENDER)
                .setUserNumber(USER_NUMBER)
                .setDateOfBirth(DAY_BIRTH, MONTH_BIRTH, YEAR_BIRTH)
                .setSubject(SUBJECTS)
                .setHobbies(HOBBIES)
                .setPicture(PICTURE)
                .setCurrentAddress(ADDRESS)
                .setState(STATE)
                .setCity(CITY)
                .submit();

        registrationPage.checkResult("Student Name", FULL_NAME)
                .checkResult("Student Email", EMAIL)
                .checkResult("Gender", GENDER)
                .checkResult("Mobile", USER_NUMBER)
                .checkResult("Date of Birth", DATE_OF_BIRTH)
                .checkResult("Subjects", SUBJECTS)
                .checkResult("Hobbies", HOBBIES)
                .checkResult("Picture", PICTURE)
                .checkResult("Address", ADDRESS)
                .checkResult("State and City", STATE_AND_CITY);
    }

    @Test
    void studentMinimumRegistrationForm() {
        registrationPage.openPage(TOOLS_QA_URL)
                .setFirstName(FIRST_NAME)
                .setLastName(LAST_NAME)
                .setGender(GENDER)
                .setUserNumber(USER_NUMBER)
                .setDateOfBirth(DAY_BIRTH, MONTH_BIRTH, YEAR_BIRTH)
                .submit();

        registrationPage.checkResult("Student Name", FULL_NAME)
                .checkResult("Gender", GENDER)
                .checkResult("Mobile", USER_NUMBER)
                .checkResult("Date of Birth", DATE_OF_BIRTH);
    }

    @Test
    void negativeStudentRegistrationForm() {
        registrationPage.openPage(TOOLS_QA_URL).submit();
        registrationPage.checkRequiredFields(CSS_PROPERTY_NAME, CSS_EXPECTED_VALUE);
    }
}
