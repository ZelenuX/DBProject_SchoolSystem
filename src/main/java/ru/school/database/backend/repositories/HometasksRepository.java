package ru.school.database.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.school.database.backend.entities.Hometasks;

public interface HometasksRepository extends JpaRepository<Hometasks, Long>, JpaSpecificationExecutor<Hometasks> {

}