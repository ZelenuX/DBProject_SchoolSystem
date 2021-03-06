package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.entities.Students;

public interface StudentsRepository extends JpaRepository<Students, Long>, JpaSpecificationExecutor<Students> {

}