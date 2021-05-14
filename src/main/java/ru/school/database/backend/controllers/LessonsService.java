package ru.school.database.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.school.database.backend.entities.Lessons;
import ru.school.database.backend.repositories.LessonsRepository;
import ru.school.database.backend.repositories.TimetableRepository;

@Service
public class LessonsService extends RepositoryService<Lessons, Long> {
    public LessonsService(@Autowired LessonsRepository lessonsRepository, @Autowired TimetableRepository timetableRepository){
        super(lessonsRepository, Lessons.class, "auto-id", "theme", "description",
                "auto-timetablecellId", "finalCombobox-timetablecell", "date");
        super.setComboboxFieldProvider("timetablecell", timetableRepository);
        super.setIdProvider("timetablecell.getId", "timetablecellId");
    }
}
