package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.entities.People;

public interface PeopleRepository extends JpaRepository<People, Long>, JpaSpecificationExecutor<People> {
}