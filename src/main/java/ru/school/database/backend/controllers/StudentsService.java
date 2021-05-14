package ru.school.database.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.Students;
import ru.school.database.backend.repositories.PeopleRepository;
import ru.school.database.backend.repositories.StudentsRepository;

@Service
public class StudentsService extends RepositoryService<Students, Long> {
    public StudentsService(@Autowired StudentsRepository studentsRepository, @Autowired PeopleRepository peopleRepository){
        super(studentsRepository, Students.class, "auto-peopleId", "finalCombobox-people", "educationStartDate");
        super.setComboboxFieldProvider("people", peopleRepository);
        super.setIdProvider("people.getId", "peopleId");
    }
}
