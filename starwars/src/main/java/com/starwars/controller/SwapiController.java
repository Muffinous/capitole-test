package com.starwars.controller;

import com.starwars.model.Person;
import com.starwars.model.Starship;
import com.starwars.service.SwapiService;
import com.starwars.sort.PersonSortRegistry;
import com.starwars.sort.StarshipSortRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RequestMapping("/")
public class SwapiController {

    @Autowired
    private SwapiService swapiService;

    @Autowired
    private StarshipSortRegistry starshipSortRegistry;

    @Autowired
    private PersonSortRegistry personSortRegistry;

    // Redirects to home.html view when accessing root URL
    @GetMapping("/")
    public String homeRedirect() {
        return "home";
    }

    // Handles requests to /people, fetching and sorting the list of people
    @GetMapping("/people")
    public String getPeople(
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String order,
            Model model
    ) {
        List<Person> people = swapiService.getSortedPeople(sort, order);

        model.addAttribute("people", people);
        model.addAttribute("sort", sort);
        model.addAttribute("order", order);
        model.addAttribute("sortKeys", personSortRegistry.getAvailableKeys());

        return "people";
    }

    // Handles requests to /starships, fetching and sorting the list of starships
    @GetMapping("/starships")
    public String getStarships(
            @RequestParam(defaultValue = "name") String sort,
            @RequestParam(defaultValue = "asc") String order,
            Model model) {

        List<Starship> starships = swapiService.getSortedStarships(sort, order);
        model.addAttribute("starships", starships);
        model.addAttribute("sort", sort);
        model.addAttribute("order", order);
        model.addAttribute("sortKeys", starshipSortRegistry.getAvailableKeys());

        return "starships";
    }
}
