package ru.zelenux.springprojects.helloworld2;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/react-app")
@CrossOrigin(origins = "http://localhost:3000")
public class ReactWebAppController {
    @GetMapping("/first-responder")
    public String firstReactPage(){
        return "[{\"name\":\"Bob\",\"age\":38}, {\"name\":\"Mark\",\"age\":42}]";
    }
}
