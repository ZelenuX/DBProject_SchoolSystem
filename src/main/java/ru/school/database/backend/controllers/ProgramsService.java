package ru.school.database.backend.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.Programs;

@Service
public class ProgramsService extends RepositoryService<Programs, Long> {
    public ProgramsService(JpaRepository<Programs, Long> repository) {
        super(repository, Programs.class, "auto-id", "name", "description");
    }
}
