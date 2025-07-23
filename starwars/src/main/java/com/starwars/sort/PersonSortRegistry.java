package com.starwars.sort;

import com.starwars.model.Person;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonSortRegistry {

    private final Map<String, Comparator<Person>> strategies = new LinkedHashMap<>();

    public PersonSortRegistry() {
        strategies.put("name", Comparator.comparing(Person::getName, String.CASE_INSENSITIVE_ORDER));
        strategies.put("created", Comparator.comparing(Person::getCreated));
    }

    public Comparator<Person> getComparator(String key, String order) {
        Comparator<Person> comparator = strategies.getOrDefault(key, strategies.get("name"));
        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public Set<String> getAvailableKeys() {
        return strategies.keySet();
    }
}
