package ru.school.database.backend.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.school.database.backend.compositeKeys.StudentsProgramsKey;
import ru.school.database.backend.entities.StudentsPrograms;
import ru.school.database.backend.repositories.ProgramsRepository;
import ru.school.database.backend.repositories.StudentsRepository;

@Service
public class StudentsProgramsService extends RepositoryService<StudentsPrograms, StudentsProgramsKey> {
    public StudentsProgramsService(JpaRepository<StudentsPrograms, StudentsProgramsKey> repository,
                                   StudentsRepository studentsRepository, ProgramsRepository programsRepository) {
        super(repository, StudentsPrograms.class,
                "auto-studentId", "finalCombobox-student",
                "auto-programId", "finalCombobox-program");
        super.setComboboxFieldProvider("student", studentsRepository);
        super.setComboboxFieldProvider("program", programsRepository);
        super.addForeignKeyProvider("student.getPeopleId", "studentId");
        super.addForeignKeyProvider("program.getId", "programId");
    }
}
