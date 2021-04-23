package com.vaadin.tutorial.crm.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.vaadin.tutorial.crm.backend.entities.Flat;

public interface FlatRepos extends JpaRepository<Flat, Long> {
}
