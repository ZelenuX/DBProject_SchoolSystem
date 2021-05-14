package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.entities.LessonMarks;

public interface LessonMarksRepository extends JpaRepository<LessonMarks, Long>, JpaSpecificationExecutor<LessonMarks> {

}