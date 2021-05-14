package ru.school.database.backend.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.Disciplines;

@Service
public class DisciplinesService extends RepositoryService<Disciplines, Long> {
    public DisciplinesService(JpaRepository<Disciplines, Long> repository) {
        super(repository, Disciplines.class, "auto-id", "name", "description", "hoursAWeek");
    }
}
