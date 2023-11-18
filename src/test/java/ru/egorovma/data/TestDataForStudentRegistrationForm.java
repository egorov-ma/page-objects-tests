package ru.egorovma.data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestDataForStudentRegistrationForm {
    private final Faker faker = new Faker(new Locale("en"));
    private final RandomDateGenerator dateOfBirthGenerator = new RandomDateGenerator(1900, 2100);
    public final static String TOOLS_QA_URL = "/automation-practice-form";
    public final static String CSS_EXPECTED_VALUE = "rgb(220, 53, 69)";
    public String firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            fullName = firstName + " " + lastName,
            emailAddress = faker.internet().emailAddress(),
            gender = faker.options().option("Male", "Female", "Other"),
            userNumber = faker.numerify("79########"),
            dayBirth = dateOfBirthGenerator.getRandomDay(),
            monthBirth = dateOfBirthGenerator.getRandomMonth(),
            yearBirth = dateOfBirthGenerator.getRandomYear(),
            dateOfBirth = dateOfBirthGenerator.getCustomFormattedDate(),
            subjects = faker.options().option("Accounting", "Biology", "Chemistry", "English", "Hindi", "Maths", "Physics"),
            hobbies = faker.options().option("Sports", "reading", "Music"),
            picture = faker.options().option("qaguru0.jpg", "qaguru1.jpg", "qaguru2.jpg"),
            address = faker.address().streetAddress(),
            state = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            city = getRandomCity(state),
            stateAndCity = state + " " + city;

    private String getRandomCity(String state) {
        return switch (state) {
            case "NCR" -> faker.options().option("Delhi", "Gurgaon", "Noida");
            case "Uttar Pradesh" -> faker.options().option("Agra", "Lucknow", "Merrut");
            case "Haryana" -> faker.options().option("Karnal", "Panipat");
            case "Rajasthan" -> faker.options().option("Jaipur", "Jaiselmer");
            default -> throw new IllegalArgumentException("Unsupported state: " + state);
        };
    }
}