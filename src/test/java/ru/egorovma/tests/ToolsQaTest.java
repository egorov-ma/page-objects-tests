package ru.egorovma.tests;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import ru.egorovma.pages.RegistrationPage;

import static com.codeborne.selenide.Selenide.$;

public class ToolsQaTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

    @Test
    void studentFullRegistrationForm() {
        registrationPage.openPage()
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

        $(".table-responsive")
                .shouldHave(Condition.text("Maksim Егоров"))
                .shouldHave(Condition.text("test@qa.ru"))
                .shouldHave(Condition.text("Male"))
                .shouldHave(Condition.text("9876543210"))
                .shouldHave(Condition.text("14 December,1993"))
                .shouldHave(Condition.text("Maths"))
                .shouldHave(Condition.text("Sports"))
                .shouldHave(Condition.text("qaguru.jpg"))
                .shouldHave(Condition.text("ул. Мир 2023, корпус 10, кв. 29"))
                .shouldHave(Condition.text("Uttar Pradesh Merrut"));
    }
}
