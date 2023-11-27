package ru.egorovma.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
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

    @MethodSource("dataProvider")
    @ParameterizedTest(name = "Рандомные, локализованные данные пользователя {0}")
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
    @ParameterizedTest(name = "Рандомные, локализованные данные пользователя {0}")
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

    @CsvFileSource(resources = "/test_data/studentTestData.csv")
    @ParameterizedTest(name = "дата {4} {5} {6}")
    @Tags({
            @Tag("WEB"),
            @Tag("Positive")
    })
    @DisplayName("Проверяем работу с календарем")
    void calendarTest(String firstName, String lastName, String gender, String userNumber,
                      String dayBirth, String monthBirth, String yearBirth) {
        registrationPage.openPage(TOOLS_QA_URL)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setGender(gender)
                .setUserNumber(userNumber)
                .setDateOfBirth(dayBirth, monthBirth, yearBirth)
                .submit();

        registrationPage.checkResult("Student Name", firstName + " " + lastName)
                .checkResult("Gender", gender)
                .checkResult("Mobile", userNumber)
                .checkResult("Date of Birth", dayBirth + " " + monthBirth + "," + yearBirth);
    }

    @MethodSource("studentHobbiesTest")
    @ParameterizedTest(name = "хобби: {0}")
    @Tags({
            @Tag("WEB"),
            @Tag("Positive")
    })
    @DisplayName("Проверяем разные наборы хобби")
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

    @ValueSource(strings = {
            "7 987 6546", "123-123-12", "7(987)4561"
    })
    @ParameterizedTest(name = "Проверка маски: {0}")
    @Tags({
            @Tag("WEB"),
            @Tag("Negative")
    })
    @DisplayName("Проверка мобильного телефона")
    void userNumberTest(String userNumber) {
        registrationPage.openPage(TOOLS_QA_URL).submit();
        registrationPage.setUserNumber(userNumber)
                .submit();
        registrationPage.checkRequiredUserNumber("border-color", CSS_EXPECTED_VALUE);
    }
}
