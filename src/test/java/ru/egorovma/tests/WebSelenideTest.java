package ru.egorovma.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import ru.egorovma.data.Language;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Проверка главной страницы selenide")
public class WebSelenideTest {

    @BeforeAll
    public static void setUp() {
        Configuration.pageLoadStrategy = "eager";
    }

    @EnumSource(Language.class)
    @ParameterizedTest(name = "Переключаем язык сайта на {0}")
    @Tag("Blocked")
    @DisplayName("Проверка переключения языка")
    void selenideSiteShouldDisplayCorrectText(Language language) {
        open("https://selenide.org/");
        $$("#languages a").find(text(language.name())).click();
        $("h3").shouldHave(text(language.getDescription()));
    }

    static Stream<Arguments> selenideSiteShouldDisplayCorrectButton() {
        return Stream.of(
                Arguments.of(
                        Language.EN,
                        List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")
                ),
                Arguments.of(
                        Language.RU,
                        List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы")
                )
        );
    }

    @MethodSource()
    @ParameterizedTest(name = "Переключаем язык сайта на {0}, проверяем в шапке кнопки - {1}")
    @Tag("Blocked")
    @DisplayName("Проверка переключения языка")
    void selenideSiteShouldDisplayCorrectButton(Language language, List<String> btn) {
        open("https://selenide.org/");
        $$("#languages a").find(text(language.name())).click();
        $$(".main-menu-pages a").filter(visible)
                .shouldHave(texts(btn));
        $("h3").shouldHave(text(language.getDescription()));
    }
}
