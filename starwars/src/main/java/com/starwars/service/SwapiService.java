package com.starwars.service;

import com.starwars.model.Person;
import com.starwars.model.Starship;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SwapiService {
    List<Person> getSortedPeople(String sortBy, String order);
    List<Starship> getSortedStarships(String sortBy, String order);
}
