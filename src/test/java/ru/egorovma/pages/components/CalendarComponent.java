package ru.egorovma.pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CalendarComponent {
    private final SelenideElement yearInput = $(".react-datepicker__year-select"),
            monthInput = $(".react-datepicker__month-select");

    public void setDate(String day, String month, String year) {
        yearInput.selectOption(year);
        monthInput.selectOption(month);
        $(".react-datepicker__day--0" + day + ":not(.react-datepicker__day--outside-month)").click();
    }
}
