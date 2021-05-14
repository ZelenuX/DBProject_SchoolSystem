package ru.school.database.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.LessonMarks;
import ru.school.database.backend.repositories.LessonMarksRepository;
import ru.school.database.backend.repositories.LessonsRepository;
import ru.school.database.backend.repositories.StudentsRepository;

@Service
public class LessonMarksService extends RepositoryService<LessonMarks, Long> {
    public LessonMarksService(@Autowired LessonMarksRepository lessonMarksRepository,
                          @Autowired StudentsRepository studentsRepository, @Autowired LessonsRepository lessonsRepository){
        super(lessonMarksRepository, LessonMarks.class, "auto-id", "value", "comment",
                "auto-studentId", "finalCombobox-student", "auto-lessonId", "finalCombobox-lesson");
        super.setComboboxFieldProvider("student", studentsRepository);
        super.addForeignKeyProvider("student.getPeopleId", "studentId");
        super.setComboboxFieldProvider("lesson", lessonsRepository);
        super.addForeignKeyProvider("lesson.getId", "lessonId");
    }
}
