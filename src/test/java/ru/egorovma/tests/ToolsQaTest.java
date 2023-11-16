package ru.egorovma.tests;

import org.junit.jupiter.api.Test;
import ru.egorovma.pages.RegistrationPage;

public class ToolsQaTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();
    private final static String TOOLS_QA_URL = "/automation-practice-form";

    @Test
    void studentFullRegistrationForm() {
        registrationPage.openPage(TOOLS_QA_URL)
                .setFirstName("Maksim")
                .setLastName("Егоров")
                .setEmail("test@qa.ru")
                .setGender("Male")
                .setUserNumber("9876543210")
                .setDateOfBirth("14", "December", "1993")
                .setSubject("Math")
                .setHobbies("Sports")
                .setHobbies("Sports", "Reading")
                .setHobbies("Sports", "Reading", "Music")
                .setPicture("qaguru.jpg")
                .setCurrentAddress("ул. Мир 2023, корпус 10, кв. 29")
                .setState("Uttar Pradesh")
                .setCity("Merrut")
                .submit();

        registrationPage.checkResult("Student Name", "Maksim Егоров")
                .checkResult("Student Email", "test@qa.ru")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9876543210")
                .checkResult("Date of Birth", "14 December,1993")
                .checkResult("Subjects", "Maths")
                .checkResult("Hobbies", "Sports, Music")
                .checkResult("Picture", "qaguru.jpg")
                .checkResult("Address", "ул. Мир 2023, корпус 10, кв. 29")
                .checkResult("State and City", "Uttar Pradesh Merrut");
    }

    @Test
    void studentMinimumRegistrationForm() {
        registrationPage.openPage(TOOLS_QA_URL)
                .setFirstName("Maksim")
                .setLastName("Егоров")
                .setGender("Male")
                .setUserNumber("9876543210")
                .setDateOfBirth("14", "December", "1993")
                .submit();

        registrationPage.checkResult("Student Name", "Maksim Егоров")
                .checkResult("Gender", "Male")
                .checkResult("Mobile", "9876543210")
                .checkResult("Date of Birth", "14 December,1993");
    }

    @Test
    void negativeStudentRegistrationForm() {
        registrationPage.openPage(TOOLS_QA_URL).submit();
        registrationPage.checkRequiredFields("border-color", "rgb(220, 53, 69)");

    }
}
