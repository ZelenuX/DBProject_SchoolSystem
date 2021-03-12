package ru.zelenux.springprojects.helloworld2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zelenux.springprojects.helloworld2.dbInteraction.entities.Flat;
import ru.zelenux.springprojects.helloworld2.dbInteraction.repositories.FlatRepos;
import ru.zelenux.springprojects.helloworld2.dbInteraction.repositories.PersonRepos;

@Controller
public class GreetingController {
    private final FlatRepos flatRepos;
    private final PersonRepos personRepos;

    @Autowired
    public GreetingController(FlatRepos flatRepos, PersonRepos personRepos) {
        this.flatRepos = flatRepos;
        this.personRepos = personRepos;
    }
    @GetMapping("/greeting")
    public String greetingForm(@RequestParam(name="name", required=false, defaultValue="Customer") String name, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("flat", new Flat());
        model.addAttribute("flats", flatRepos.findAll());
        return "greeting";
    }
    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Flat flat,
            @RequestParam(name="name", required=false, defaultValue="World") String name, Model model){
        System.out.println("\t" + flat.getId() + ": " + flat.getPrice() + "$ " + flat.getSqrMeters() + "m^2");
        model.addAttribute("flat", flatRepos.save(flat));
        return "result";
    }
}
