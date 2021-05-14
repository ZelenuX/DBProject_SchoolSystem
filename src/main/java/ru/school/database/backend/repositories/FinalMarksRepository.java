package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.compositeKeys.FinalMarksKey;
import ru.school.database.backend.entities.FinalMarks;

public interface FinalMarksRepository extends JpaRepository<FinalMarks, FinalMarksKey>, JpaSpecificationExecutor<FinalMarks> {

}