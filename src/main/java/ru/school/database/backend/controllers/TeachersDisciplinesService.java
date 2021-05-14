package ru.school.database.backend.controllers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import ru.school.database.backend.compositeKeys.TeachersDisciplinesKey;
import ru.school.database.backend.entities.TeachersDisciplines;
import ru.school.database.backend.repositories.DisciplinesRepository;
import ru.school.database.backend.repositories.TeachersRepository;

@Service
public class TeachersDisciplinesService extends RepositoryService<TeachersDisciplines, TeachersDisciplinesKey> {
    public TeachersDisciplinesService(JpaRepository<TeachersDisciplines, TeachersDisciplinesKey> repository,
                                      TeachersRepository teachersRepository, DisciplinesRepository disciplinesRepository) {
        super(repository, TeachersDisciplines.class,
                "auto-teacherId", "finalCombobox-teacher",
                "auto-disciplineId", "finalCombobox-discipline");
        super.setComboboxFieldProvider("teacher", teachersRepository);
        super.setComboboxFieldProvider("discipline", disciplinesRepository);
        super.addForeignKeyProvider("teacher.getPeopleId", "teacherId");
        super.addForeignKeyProvider("discipline.getId", "disciplineId");
    }
}
