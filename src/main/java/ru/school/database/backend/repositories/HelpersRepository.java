package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.entities.Helpers;

public interface HelpersRepository extends JpaRepository<Helpers, Long>, JpaSpecificationExecutor<Helpers> {

}