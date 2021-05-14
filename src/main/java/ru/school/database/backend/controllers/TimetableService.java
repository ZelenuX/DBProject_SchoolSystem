package ru.school.database.backend.controllers;

import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.Timetable;
import ru.school.database.backend.repositories.TimetableRepository;
import ru.school.database.backend.repositories.DisciplinesRepository;
import ru.school.database.backend.repositories.ProgramsRepository;
import ru.school.database.backend.repositories.TeachersRepository;

@Service
public class TimetableService extends RepositoryService<Timetable, Long> {
    public TimetableService(TimetableRepository repository, ProgramsRepository programsRepository,
                            TeachersRepository teachersRepository, DisciplinesRepository disciplinesRepository) {
        super(repository, Timetable.class, "auto-id",
                "auto-programId", "finalCombobox-program",
                "auto-teacherId", "finalCombobox-teacher",
                "auto-disciplineId", "finalCombobox-discipline",
                "day", "time-time", "room");
        super.setComboboxFieldProvider("program", programsRepository);
        super.setComboboxFieldProvider("teacher", teachersRepository);
        super.setComboboxFieldProvider("discipline", disciplinesRepository);
        super.addForeignKeyProvider("program.getId", "programId");
        super.addForeignKeyProvider("teacher.getPeopleId", "teacherId");
        super.addForeignKeyProvider("discipline.getId", "disciplineId");
    }
}
