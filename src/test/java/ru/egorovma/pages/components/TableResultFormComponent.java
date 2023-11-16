package ru.egorovma.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class TableResultFormComponent {
    private final SelenideElement tableResponsiveInput = $(".table-responsive");

    public void checkResult(String key, String value) {
        tableResponsiveInput.$(byText(key)).sibling(0).shouldHave(text(value));
    }
}