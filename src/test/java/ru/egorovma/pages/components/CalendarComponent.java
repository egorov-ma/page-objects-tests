package ru.egorovma.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final SelenideElement chooseYear = $(".react-datepicker__year-select"),
            chooseMonth = $(".react-datepicker__month-select"),
            chooseDay = $(".react-datepicker__month:not(.react-datepicker__day--outside-month)");

    public void setDate(String day, String month, String year) {
        chooseYear.selectOption(year);
        chooseMonth.selectOption(month);
        chooseDay.$(byText(day)).click();
    }
}