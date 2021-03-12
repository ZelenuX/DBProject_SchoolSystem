package ru.zelenux.springprojects.helloworld2.dbInteraction.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.zelenux.springprojects.helloworld2.dbInteraction.entities.Flat;

public interface FlatRepos extends CrudRepository<Flat, Long> {
}
