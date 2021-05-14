package ru.school.database.backend.forComplexQueries;

import ru.school.database.backend.entities.People;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class PeopleByIdFinderImpl implements PeopleByIdFinder {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public People getById(Integer id) {
        Query query = entityManager.createQuery("select p from People p where p.id = " + id);
        List<People> resList = query.getResultList();
        return resList.get(0);
    }
}
