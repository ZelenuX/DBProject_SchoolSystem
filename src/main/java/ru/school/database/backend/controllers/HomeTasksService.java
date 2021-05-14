package ru.school.database.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.Hometasks;
import ru.school.database.backend.repositories.HometasksRepository;
import ru.school.database.backend.repositories.LessonsRepository;

@Service
public class HomeTasksService extends RepositoryService<Hometasks, Long> {
    public HomeTasksService(@Autowired HometasksRepository hometasksRepository, @Autowired LessonsRepository lessonsRepository){
        super(hometasksRepository, Hometasks.class, "auto-lessonId", "finalCombobox-lesson", "text", "deadline");
        super.setComboboxFieldProvider("lesson", lessonsRepository);
        super.setIdProvider("lesson.getId", "lessonId");
    }
}
