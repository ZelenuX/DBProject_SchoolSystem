package ru.school.database.backend.controllers;

import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.People;
import ru.school.database.backend.repositories.PeopleRepository;

@Service
public class PeopleService extends RepositoryService<People, Long> {
    public PeopleService(PeopleRepository repository) {
        super(repository, People.class, "auto-id", "firstName", "secondName", "lastName", "birthday");
        People p = repository.getById(1);
        System.out.println(p);
    }
}
