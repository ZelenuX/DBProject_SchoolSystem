package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.entities.Teachers;

public interface TeachersRepository extends JpaRepository<Teachers, Long>, JpaSpecificationExecutor<Teachers> {

}