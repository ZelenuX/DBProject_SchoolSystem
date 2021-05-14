package ru.school.database.backend.forComplexQueries;

import lombok.Getter;

@Getter
public class BestStudent {
    private String firstName, secondName, lastName;
    private String value;

    public BestStudent(String firstName, String secondName, String lastName, String value) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.lastName = lastName;
        this.value = value;
    }
}
