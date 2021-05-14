package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.compositeKeys.TeachersDisciplinesKey;
import ru.school.database.backend.entities.TeachersDisciplines;

public interface TeachersDisciplinesRepository extends JpaRepository<TeachersDisciplines, TeachersDisciplinesKey>, JpaSpecificationExecutor<TeachersDisciplines> {

}