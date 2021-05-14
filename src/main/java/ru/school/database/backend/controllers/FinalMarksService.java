package ru.school.database.backend.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.school.database.backend.compositeKeys.FinalMarksKey;
import ru.school.database.backend.entities.FinalMarks;
import ru.school.database.backend.repositories.DisciplinesRepository;
import ru.school.database.backend.repositories.StudentsRepository;

@Service
public class FinalMarksService extends RepositoryService<FinalMarks, FinalMarksKey> {
    public FinalMarksService(JpaRepository<FinalMarks, FinalMarksKey> repository,
                             StudentsRepository studentsRepository, DisciplinesRepository disciplinesRepository) {
        super(repository, FinalMarks.class,
                "auto-studentId", "finalCombobox-student",
                "auto-disciplineId", "finalCombobox-discipline",
                "value", "comment");
        super.setComboboxFieldProvider("student", studentsRepository);
        super.setComboboxFieldProvider("discipline", disciplinesRepository);
        super.addForeignKeyProvider("student.getPeopleId", "studentId");
        super.addForeignKeyProvider("discipline.getId", "disciplineId");
    }
}
