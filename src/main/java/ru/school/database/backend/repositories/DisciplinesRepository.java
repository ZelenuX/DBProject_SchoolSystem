package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.entities.Disciplines;

public interface DisciplinesRepository extends JpaRepository<Disciplines, Long>, JpaSpecificationExecutor<Disciplines> {

}