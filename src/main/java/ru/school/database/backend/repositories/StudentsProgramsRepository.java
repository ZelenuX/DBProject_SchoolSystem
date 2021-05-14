package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.compositeKeys.StudentsProgramsKey;
import ru.school.database.backend.entities.StudentsPrograms;

public interface StudentsProgramsRepository extends JpaRepository<StudentsPrograms, StudentsProgramsKey>, JpaSpecificationExecutor<StudentsPrograms> {

}