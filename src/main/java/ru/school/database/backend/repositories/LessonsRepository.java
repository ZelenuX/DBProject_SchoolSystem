package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.entities.Lessons;

public interface LessonsRepository extends JpaRepository<Lessons, Long>, JpaSpecificationExecutor<Lessons> {

}