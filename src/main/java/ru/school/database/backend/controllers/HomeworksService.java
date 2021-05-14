package ru.school.database.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.Homeworks;
import ru.school.database.backend.repositories.HomeworksRepository;
import ru.school.database.backend.repositories.HometasksRepository;
import ru.school.database.backend.repositories.StudentsRepository;

@Service
public class HomeworksService extends RepositoryService<Homeworks, Long> {
    public HomeworksService(@Autowired HomeworksRepository homeworksRepository,
                              @Autowired StudentsRepository studentsRepository, @Autowired HometasksRepository hometasksRepository){
        super(homeworksRepository, Homeworks.class, "auto-id", "homework", "studentsComment",
                "auto-studentId", "finalCombobox-student", "auto-hometaskId", "hometask",
                "mark", "teachersComment");
        super.setComboboxFieldProvider("student", studentsRepository);
        super.setIdProvider("student.getPeopleId", "studentId");
        super.setComboboxFieldProvider("hometask", hometasksRepository);
        super.setIdProvider("hometask.getLessonId", "hometaskId");
    }
}
