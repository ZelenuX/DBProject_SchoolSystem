package ru.school.database.backend.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.People;

@Service
public class PeopleService extends RepositoryService<People, Long> {
    public PeopleService(JpaRepository<People, Long> repository) {
        super(repository, People.class, "auto-id", "firstName", "secondName", "lastName", "birthday");
    }
}
