package com.starwars.service.impl;

import com.starwars.model.PeopleResponse;
import com.starwars.model.Person;
import com.starwars.model.Starship;
import com.starwars.model.StarshipsResponse;
import com.starwars.service.SwapiService;
import com.starwars.sort.PersonSortRegistry;
import com.starwars.sort.StarshipSortRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SwapiServiceImpl implements SwapiService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "https://swapi.py4e.com/api/";
    private static final String PEOPLE_URL = BASE_URL + "people/";
    private static final String STARSHIPS_URL = BASE_URL + "starships/";

    @Autowired
    private PersonSortRegistry personSortRegistry;

    @Autowired
    private StarshipSortRegistry starshipSortRegistry;

    @Override
    public List<Person> getSortedPeople(String sortBy, String order) {
        List<Person> people = fetchAllPeople();
        Comparator<Person> comparator = personSortRegistry.getComparator(sortBy, order);
        people.sort(comparator);
        return people;
    }

    public List<Person> fetchAllPeople() {
        List<Person> allPeople = new ArrayList<>();
        String url = PEOPLE_URL;

        while (url != null) {
            ResponseEntity<PeopleResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<PeopleResponse>() {}
            );

            PeopleResponse peopleResponse = response.getBody();
            if (peopleResponse != null) {
                allPeople.addAll(peopleResponse.getResults());
                url = peopleResponse.getNext();
            } else {
                url = null;
            }
        }

        return allPeople;
    }

    @Override
    public List<Starship> getSortedStarships(String sortBy, String order) {
        List<Starship> starships = fetchAllStarships();
        Comparator<Starship> comparator = starshipSortRegistry.getComparator(sortBy, order);
        starships.sort(comparator);
        return starships;
    }

    public List<Starship> fetchAllStarships() {
        List<Starship> allStarships = new ArrayList<>();
        String url = STARSHIPS_URL;

        while (url != null) {
            ResponseEntity<StarshipsResponse> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<StarshipsResponse>() {}
            );

            StarshipsResponse starshipsResponse = response.getBody();
            if (starshipsResponse != null) {
                allStarships.addAll(starshipsResponse.getResults());
                url = starshipsResponse.getNext();
            } else {
                url = null;
            }
        }

        return allStarships;
    }
}
