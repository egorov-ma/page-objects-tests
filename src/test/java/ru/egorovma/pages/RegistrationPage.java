package ru.egorovma.pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import ru.egorovma.pages.components.CalendarComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {
    CalendarComponent calendarComponent = new CalendarComponent();
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement userEmailInput = $("#userEmail");
    private final SelenideElement genterWrapperInput = $("#genterWrapper");
    private final SelenideElement userNumberInput = $("#userNumber");
    private final SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    private final SelenideElement subjectsInput = $("#subjectsInput");
    private final SelenideElement hobbiesSportsInput = $("[for=hobbies-checkbox-1]");
    private final SelenideElement hobbiesReadingInput = $("[for=hobbies-checkbox-2]");
    private final SelenideElement hobbiesMusicInput = $("[for=hobbies-checkbox-3]");
    private final SelenideElement uploadPictureInput = $("#uploadPicture");
    private final SelenideElement currentAddressInput = $("#currentAddress");
    private final SelenideElement stateInput = $("#react-select-3-input");
    private final SelenideElement cityInput = $("#react-select-4-input");
    private final SelenideElement submitInput = $("#submit");


    public RegistrationPage openPage() {
        Configuration.baseUrl = "https://demoqa.com";
        open("/automation-practice-form");
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstName(String firstName) {
        firstNameInput.setValue(firstName);
        return this;
    }

    public RegistrationPage setLastName(String lastName) {
        lastNameInput.setValue(lastName);
        return this;
    }

    public RegistrationPage setEmail(String userEmail) {
        userEmailInput.setValue(userEmail);
        return this;
    }

    public RegistrationPage setGender(String genterWrapper) {
        genterWrapperInput.$(byText(genterWrapper)).click();
        return this;
    }

    public RegistrationPage setUserNumber(String userNumber) {
        userNumberInput.setValue(userNumber);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubject(String subject) {
        subjectsInput.setValue(subject).pressEnter();
        return this;
    }

    public RegistrationPage setHobbies(String subject) {
        if (subject.equals("Sports")) {
            hobbiesSportsInput.click();
        }
        if (subject.equals("Reading")) {
            hobbiesReadingInput.click();
        }
        if (subject.equals("Music")) {
            hobbiesMusicInput.click();
        }
        return this;
    }

    public RegistrationPage setHobbies(String subject1, String subject2) {
        if (subject1.equals("Sports") || subject2.equals("Sports")) {
            hobbiesSportsInput.click();
        }
        if (subject1.equals("Reading") || subject2.equals("Reading")) {
            hobbiesReadingInput.click();
        }
        if (subject1.equals("Music") || subject2.equals("Music")) {
            hobbiesMusicInput.click();
        }
        return this;
    }

    public RegistrationPage setHobbies(String subject1, String subject2, String subject3) {
        if (subject1.equals("Sports") || subject2.equals("Sports") || subject3.equals("Sports")) {
            hobbiesSportsInput.click();
        }
        if (subject1.equals("Reading") || subject2.equals("Reading") || subject3.equals("Reading")) {
            hobbiesReadingInput.click();
        }
        if (subject1.equals("Music") || subject2.equals("Music") || subject3.equals("Music")) {
            hobbiesMusicInput.click();
        }
        return this;
    }

    public RegistrationPage setPicture(String picture) {
        uploadPictureInput.uploadFromClasspath(picture);
        return this;
    }
    public RegistrationPage setCurrentAddress(String currentAddress) {
        currentAddressInput.setValue(currentAddress);
        return this;
    }
    public RegistrationPage setState(String state) {
        stateInput.setValue(state).pressEnter();
        return this;
    }
    public RegistrationPage setCity(String city) {
        cityInput.setValue(city).pressEnter();
        return this;
    }
    public RegistrationPage submit() {
        submitInput.click();
        return this;
    }
}
