package ru.egorovma.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

//@Disabled("Не актуален, использовался как песочница")
@DisplayName("Параметризованные тесты")
public class WebTest {
    @BeforeAll
    public static void setUp() {
        Configuration.pageLoadStrategy = "eager";
    }

    @BeforeEach
    void precondition() {
        open("https://duckduckgo.com/");
    }

    @Test
//    @AllureId() // айдишник теста
    @Tags({     // тут можно указать приоритет и критичность теста
            @Tag("Blocked"),
            @Tag("Web"),
            @Tag("SMOKE")
    })
    @DisplayName("Для поискового запроса 'selenide' должено отдаваться не пустое значение")
        // название теста
    void successfulSearchSelenideTest() {
        // описываем шаги
        $("#searchbox_input").setValue("selenide").pressEnter();

        // проверяем ожидаемый результат
        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(sizeGreaterThan(0));
    }

    @Test
    @Tags({
            @Tag("Low"),
            @Tag("Web")
    })
    @DisplayName("Для поискового запроса 'selenide' должено отдаваться не пусто список фото")
    void successfulSearchPhotoTest() {
        $("#searchbox_input").setValue("selenide").pressEnter();
        $("[data-zci-link='images']").click();
        $$("img.tile--img__img")
                .shouldBe(sizeGreaterThan(0));
    }

    @ValueSource(strings = {
            "selenide", "Junit 5"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должено отдаваться не пустое значение")
    @Tags({
            @Tag("Blocked"),
            @Tag("Web"),
            @Tag("SMOKE")
    })
    @DisplayName("Проверка результатов поиска")
    void successfulSearchTest(String searchQuery) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$("[data-testid='mainline'] li[data-layout='organic']")
                .shouldBe(sizeGreaterThan(0));
    }

    @CsvSource(value = {
            "selenide,https://selenide.org",
            "Junit 5,https://junit.org"
    }, delimiter = ',')
    @ParameterizedTest(name = "Для поискового запроса {0} в первой строке должна быть ссылка на {1}")
    @Tags({
            @Tag("Blocked"),
            @Tag("Web"),
            @Tag("SMOKE")
    })
    @DisplayName("Проверка результатов поиска")
    void successfulSearchByUriTest(String searchQuery, String uri) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']")
                .shouldHave(text(uri));
    }

    @CsvFileSource(resources = "/test_data/successfulSearchByUriWithFileTest.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой строке должна быть ссылка на {1}")
    @Tag("Blocked")
    @DisplayName("Проверка результатов поиска")
    void successfulSearchByUriWithFileTest(String searchQuery, String uri) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']")
                .shouldHave(text(uri));
    }
}