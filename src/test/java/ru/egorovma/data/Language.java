package ru.egorovma.data;

public enum Language {
    RU("ЧТО ТАКОЕ SELENIDE?"),
    EN("WHAT IS SELENIDE?");

    public String getDescription() {
        return description;
    }

    private final String description;

    Language(String description) {
        this.description = description;
    }
}
