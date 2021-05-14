package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.entities.Homeworks;

public interface HomeworksRepository extends JpaRepository<Homeworks, Long>, JpaSpecificationExecutor<Homeworks> {

}