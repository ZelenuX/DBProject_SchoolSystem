package com.vaadin.tutorial.crm.backend.repositories;

import com.vaadin.tutorial.crm.backend.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepos extends JpaRepository<Person, Long> {
    @Query("select c from Person c " +
            "where lower(c.name) like lower(concat('%', :nameOrSurnameSubstring, '%')) " +
            "or lower(c.surname) like lower(concat('%', :nameOrSurnameSubstring, '%'))")
    List<Person> search(@Param("nameOrSurnameSubstring") String nameOrSurnameSubstring);
}
