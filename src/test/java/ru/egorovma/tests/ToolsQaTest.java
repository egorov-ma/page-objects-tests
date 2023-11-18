package ru.egorovma.tests;

import org.junit.jupiter.api.Test;
import ru.egorovma.data.TestDataForStudentRegistrationForm;
import ru.egorovma.pages.RegistrationPage;

import static ru.egorovma.data.TestDataForStudentRegistrationForm.*;

public class ToolsQaTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TestDataForStudentRegistrationForm data = new TestDataForStudentRegistrationForm();

    @Test
    void studentFullRegistrationForm() {
        registrationPage.openPage(TOOLS_QA_URL)
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setEmail(data.emailAddress)
                .setGender(data.gender)
                .setUserNumber(data.userNumber)
                .setDateOfBirth(data.dayBirth, data.monthBirth, data.yearBirth)
                .setSubject(data.subjects)
                .setHobbies(data.hobbies)
                .setPicture(data.picture)
                .setCurrentAddress(data.address)
                .setState(data.state)
                .setCity(data.city)
                .submit();

        registrationPage.checkResult("Student Name", data.fullName)
                .checkResult("Student Email", data.emailAddress)
                .checkResult("Gender", data.gender)
                .checkResult("Mobile", data.userNumber)
                .checkResult("Date of Birth", data.dateOfBirth)
                .checkResult("Subjects", data.subjects)
                .checkResult("Hobbies", data.hobbies)
                .checkResult("Picture", data.picture)
                .checkResult("Address", data.address)
                .checkResult("State and City", data.stateAndCity);
    }

    @Test
    void studentMinimumRegistrationForm() {
        registrationPage.openPage(TOOLS_QA_URL)
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setGender(data.gender)
                .setUserNumber(data.userNumber)
                .setDateOfBirth(data.dayBirth, data.monthBirth, data.yearBirth)
                .submit();

        registrationPage.checkResult("Student Name", data.fullName)
                .checkResult("Gender", data.gender)
                .checkResult("Mobile", data.userNumber)
                .checkResult("Date of Birth", data.dateOfBirth);
    }

    @Test
    void negativeStudentRegistrationForm() {
        registrationPage.openPage(TOOLS_QA_URL).submit();
        registrationPage.checkRequiredFields("border-color", CSS_EXPECTED_VALUE);
    }
}
