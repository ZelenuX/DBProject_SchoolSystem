package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.compositeKeys.ProgramsDisciplinesKey;
import ru.school.database.backend.entities.ProgramsDisciplines;

public interface ProgramsDisciplinesRepository extends JpaRepository<ProgramsDisciplines, ProgramsDisciplinesKey>, JpaSpecificationExecutor<ProgramsDisciplines> {

}