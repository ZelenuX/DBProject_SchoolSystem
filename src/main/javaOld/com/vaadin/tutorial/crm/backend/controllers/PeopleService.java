package com.vaadin.tutorial.crm.backend.controllers;

import com.vaadin.tutorial.crm.backend.entities.Flat;
import com.vaadin.tutorial.crm.backend.entities.Person;
import com.vaadin.tutorial.crm.backend.repositories.FlatRepos;
import com.vaadin.tutorial.crm.backend.repositories.PersonRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;

@Service
public class PeopleService {
    private final PersonRepos personRepos;
    private final FlatRepos flatRepos;

    @Autowired
    public PeopleService(PersonRepos personRepos, FlatRepos flatRepos) {
        this.personRepos = personRepos;
        this.flatRepos = flatRepos;
    }

    public String addPersonPage(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("people", personRepos.findAll());
        model.addAttribute("flat", new Flat());
        model.addAttribute("allFlats", flatRepos.findAll());
        return "addPerson";
    }
    public boolean addPerson(Person person){
        if (person.isInitialized()) {
            personRepos.save(person);
            return true;
        }
        return false;
    }
    public List<Person> findAll(String nameOrSurnameFilter){
        if (nameOrSurnameFilter == null || nameOrSurnameFilter.isEmpty()){
            return personRepos.findAll();
        }
        return personRepos.search(nameOrSurnameFilter);
    }
}
