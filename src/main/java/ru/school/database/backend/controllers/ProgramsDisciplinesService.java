package ru.school.database.backend.controllers;

import org.springframework.stereotype.Service;
import ru.school.database.backend.compositeKeys.ProgramsDisciplinesKey;
import ru.school.database.backend.entities.ProgramsDisciplines;
import ru.school.database.backend.repositories.ProgramsDisciplinesRepository;
import ru.school.database.backend.repositories.DisciplinesRepository;
import ru.school.database.backend.repositories.ProgramsRepository;
import ru.school.database.backend.repositories.TeachersRepository;

@Service
public class ProgramsDisciplinesService extends RepositoryService<ProgramsDisciplines, ProgramsDisciplinesKey> {
    public ProgramsDisciplinesService(ProgramsDisciplinesRepository repository, ProgramsRepository programsRepository,
                                      TeachersRepository teachersRepository, DisciplinesRepository disciplinesRepository) {
        super(repository, ProgramsDisciplines.class,
                "auto-programId", "finalCombobox-program",
                "auto-teacherId", "finalCombobox-teacher",
                "auto-disciplineId", "finalCombobox-discipline");
        super.setComboboxFieldProvider("program", programsRepository);
        super.setComboboxFieldProvider("teacher", teachersRepository);
        super.setComboboxFieldProvider("discipline", disciplinesRepository);
        super.addForeignKeyProvider("program.getId", "programId");
        super.addForeignKeyProvider("teacher.getPeopleId", "teacherId");
        super.addForeignKeyProvider("discipline.getId", "disciplineId");
    }
}
