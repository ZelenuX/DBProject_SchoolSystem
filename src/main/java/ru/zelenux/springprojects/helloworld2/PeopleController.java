package ru.zelenux.springprojects.helloworld2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.zelenux.springprojects.helloworld2.dbInteraction.entities.Flat;
import ru.zelenux.springprojects.helloworld2.dbInteraction.entities.Person;
import ru.zelenux.springprojects.helloworld2.dbInteraction.repositories.FlatRepos;
import ru.zelenux.springprojects.helloworld2.dbInteraction.repositories.PersonRepos;

@Controller
public class PeopleController {
    private final PersonRepos personRepos;
    private final FlatRepos flatRepos;

    @Autowired
    public PeopleController(PersonRepos personRepos, FlatRepos flatRepos) {
        this.personRepos = personRepos;
        this.flatRepos = flatRepos;
    }
    @GetMapping("/addPerson")
    public String addPersonPage(Model model){
        model.addAttribute("person", new Person());
        model.addAttribute("people", personRepos.findAll());
        model.addAttribute("flat", new Flat());
        model.addAttribute("allFlats", flatRepos.findAll());
        return "addPerson";
    }
    @PostMapping("/addPerson")
    public String addPersonSubmit(@ModelAttribute Person person, Model model){
        if (person.isInitialized()) {
            personRepos.save(person);
        }
        return addPersonPage(model);
    }
}
