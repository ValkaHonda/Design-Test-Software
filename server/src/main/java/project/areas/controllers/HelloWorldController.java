package project.areas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenny on 2/26/2019.
 */
@RestController
public class HelloWorldController {
    @GetMapping("/Home")
    public List<String> getAnimals(){
        List<String> animals = new ArrayList<>();
        animals.add("Tom");
        animals.add("Garfield");
        animals.add("Tom");
        animals.add("Tom");
        return animals;
    }
}
