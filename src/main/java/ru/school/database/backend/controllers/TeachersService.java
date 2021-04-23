package ru.school.database.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.Teachers;
import ru.school.database.backend.repositories.PeopleRepository;
import ru.school.database.backend.repositories.TeachersRepository;

@Service
public class TeachersService extends RepositoryService<Teachers, Long> {
    public TeachersService(@Autowired TeachersRepository teachersRepository, @Autowired PeopleRepository peopleRepository) {
        super(teachersRepository, Teachers.class,
                "auto-peopleId", "combobox-people", "experienceYears", "educationDegree", "maxHoursAWeek", "aboutMe");
        super.setComboboxFieldProvider("people", peopleRepository);
        super.setIdProvider("people.getId", "peopleId");
    }
}
