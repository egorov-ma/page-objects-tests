package ru.egorovma.tests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@Disabled("Не актуален, убрать из проекта")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ToolsQaTestOld {
    @BeforeAll
    static void beforeAll() {
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
    }

    @BeforeEach
    void beforeEach() {
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
    }

    @Test
    @Order(1)
    void studentRegistrationForm() {
        // Заполнение формы
        $("#firstName").setValue("Maksim");
        $("#lastName").setValue("Егоров");
        $("#userEmail").setValue("test@qa.ru");
        $("#genterWrapper").$(byText("Male")).click();
        $("#userNumber").setValue("9876543210");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption("1993");
        $(".react-datepicker__month-select").selectOption("December");
        $(".react-datepicker__day--014").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("[for=hobbies-checkbox-1]").click();
        $("#uploadPicture").uploadFromClasspath("qaguru.jpg");
        $("#currentAddress").setValue("ул. Мир 2023, корпус 10, кв. 29");
        $("#react-select-3-input").setValue("Uttar Pradesh").pressEnter();
        $("#react-select-4-input").setValue("Merrut").pressEnter();
        $("#submit").click();

        // Проверка результатов
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
