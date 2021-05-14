package ru.school.database.backend.forComplexQueries;

import ru.school.database.backend.entities.People;

public interface PeopleByIdFinder {
    People getById(Integer id);
}
