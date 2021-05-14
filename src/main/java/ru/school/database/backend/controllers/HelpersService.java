package ru.school.database.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.Helpers;
import ru.school.database.backend.repositories.HelpersRepository;
import ru.school.database.backend.repositories.PeopleRepository;

@Service
public class HelpersService extends RepositoryService<Helpers, Long> {
    public HelpersService(@Autowired HelpersRepository helpersRepository, @Autowired PeopleRepository peopleRepository){
        super(helpersRepository, Helpers.class, "auto-peopleId", "finalCombobox-people", "work");
        super.setComboboxFieldProvider("people", peopleRepository);
        super.setIdProvider("people.getId", "peopleId");
    }
}
