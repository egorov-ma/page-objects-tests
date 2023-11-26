package ru.egorovma.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.egorovma.data.TestDataForStudentRegistrationForm;
import ru.egorovma.pages.RegistrationPage;

import java.util.List;
import java.util.stream.Stream;

import static ru.egorovma.data.TestDataForStudentRegistrationForm.CSS_EXPECTED_VALUE;
import static ru.egorovma.data.TestDataForStudentRegistrationForm.TOOLS_QA_URL;

//todo попробовать запускать через gradle только SMOKE тесты

@DisplayName("Проверка экранной формы регистрации студента")
public class ToolsQaTest extends TestBase {
    RegistrationPage registrationPage = new RegistrationPage();
    TestDataForStudentRegistrationForm data = new TestDataForStudentRegistrationForm("en");

    static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(
                        new TestDataForStudentRegistrationForm("ru")
                ),
                Arguments.of(
                        new TestDataForStudentRegistrationForm("en")
                )
        );
    }

    @MethodSource("dataProvider")
    @ParameterizedTest(name = "Параметризованный тест")
    @Tags({
            @Tag("SMOKE"),
            @Tag("WEB"),
            @Tag("Positive")
    })
    @DisplayName("Проверка заполнения всех полей")
    void studentFullRegistrationForm(TestDataForStudentRegistrationForm data) {
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


    @MethodSource("dataProvider")
    @ParameterizedTest(name = "Параметризованный тест")
    @Tags({
            @Tag("WEB"),
            @Tag("Positive")
    })
    @DisplayName("Проверка заполнения минимального кол-ва полей")
    void studentMinimumRegistrationForm(TestDataForStudentRegistrationForm data) {
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

    static Stream<Arguments> studentHobbiesTest() {
        return Stream.of(
                Arguments.of(
                        List.of("Sports", "Reading", "Music")
                ),
                Arguments.of(
                        List.of("Sports", "Reading")
                ),
                Arguments.of(
                        List.of("Sports", "Music")
                ),
                Arguments.of(
                        List.of("Reading", "Music")
                )
        );
    }

    @MethodSource("studentHobbiesTest")
    @ParameterizedTest(name = "Проверяем разные наборы хобби: {0}")
    @Tags({
            @Tag("WEB"),
            @Tag("Positive")
    })
    @DisplayName("Проверка заполнения минимального кол-ва полей")
    void studentHobbiesTest(List<String> hobbies) {
        registrationPage.openPage(TOOLS_QA_URL)
                .setFirstName(data.firstName)
                .setLastName(data.lastName)
                .setGender(data.gender)
                .setUserNumber(data.userNumber)
                .setDateOfBirth(data.dayBirth, data.monthBirth, data.yearBirth)
                .setHobbies(hobbies)
                .submit();

        registrationPage.checkResult("Student Name", data.fullName)
                .checkResult("Gender", data.gender)
                .checkResult("Mobile", data.userNumber)
                .checkResult("Date of Birth", data.dateOfBirth)
                .checkResult("Hobbies", hobbies);
    }

    @Test
    @Tags({
            @Tag("WEB"),
            @Tag("Negative")
    })
    @DisplayName("Проверка отправки не заполненной ЭФ")
    void negativeStudentRegistrationForm() {
        registrationPage.openPage(TOOLS_QA_URL).submit();
        registrationPage.checkRequiredFields("border-color", CSS_EXPECTED_VALUE);
    }
}
