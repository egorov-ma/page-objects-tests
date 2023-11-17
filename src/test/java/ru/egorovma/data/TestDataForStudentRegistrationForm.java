package ru.egorovma.data;

import com.github.javafaker.Faker;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;

public class TestDataForStudentRegistrationForm {
    private static final Faker faker = new Faker(new Locale("en"));
    private static final RandomDateGenerator dateOfBirthGenerator = new RandomDateGenerator(1900, 2100);
    private static final String USER_NUMBER_FORMAT = "79########",
            STATE_NCR = "NCR",
            STATE_UTTAR_PRADESH = "Uttar Pradesh",
            STATE_HARYANA = "Haryana",
            STATE_RAJASTHAN = "Rajasthan";
    private static final List<String> GENDER_LIST = Arrays.asList("Male", "Female", "Other"),
            SUBJECTS_LIST = Arrays.asList("Accounting","Biology","Chemistry","English","Hindi","Maths","Physics"),
            HOBBIES_LIST = Arrays.asList("Sports", "reading", "Music"),
            FILE_LIST = Arrays.asList("qaguru0.jpg", "qaguru1.jpg", "qaguru2.jpg"),
            STATE_LIST = Arrays.asList("NCR", "Uttar Pradesh", "Haryana", "Rajasthan"),
            NCR_CITIES_LIST = Arrays.asList("Delhi", "Gurgaon", "Noida"),
            UTTAR_PRADESH_CITIES_LIST = Arrays.asList("Agra", "Lucknow", "Merrut"),
            HARYANA_CITIES_LIST = Arrays.asList("Karnal", "Panipat"),
            RAJASTHAN_CITIES_LIST = Arrays.asList("Jaipur", "Jaiselmer");

    public static String FIRST_NAME = faker.name().firstName(),
            LAST_NAME = faker.name().lastName(),
            FULL_NAME = FIRST_NAME + " " + LAST_NAME,
            EMAIL = faker.internet().emailAddress(),
            GENDER = getRandomItemFromList(GENDER_LIST),
            USER_NUMBER = faker.numerify(USER_NUMBER_FORMAT),
            DAY_BIRTH = dateOfBirthGenerator.getRandomDay(),
            MONTH_BIRTH = dateOfBirthGenerator.getRandomMonth(),
            YEAR_BIRTH = dateOfBirthGenerator.getRandomYear(),
            DATE_OF_BIRTH = dateOfBirthGenerator.getCustomFormattedDate(),
            SUBJECTS = getRandomItemFromList(SUBJECTS_LIST),
            HOBBIES = getRandomItemFromList(HOBBIES_LIST),
            PICTURE = getRandomItemFromList(FILE_LIST),
            ADDRESS = faker.address().streetAddress(),
            STATE = getRandomItemFromList(STATE_LIST),
            CITY = getRandomCity(STATE),
            STATE_AND_CITY = STATE + " " + CITY;

    private static String getRandomCity(String state) {
        return switch (state) {
            case STATE_NCR -> getRandomItemFromList(NCR_CITIES_LIST);
            case STATE_UTTAR_PRADESH -> getRandomItemFromList(UTTAR_PRADESH_CITIES_LIST);
            case STATE_HARYANA -> getRandomItemFromList(HARYANA_CITIES_LIST);
            case STATE_RAJASTHAN -> getRandomItemFromList(RAJASTHAN_CITIES_LIST);
            default -> throw new IllegalArgumentException("Unsupported state: " + state);
        };
    }

    private static String getRandomItemFromList(List<String> list) {
        int index = ThreadLocalRandom.current().nextInt(0, list.size());
        return list.get(index);
    }
}