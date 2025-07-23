package com.starwars.sort;

import com.starwars.model.Person;
import com.starwars.model.Starship;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class StarshipSortRegistry {

    private final Map<String, Comparator<Starship>> strategies = new LinkedHashMap<>();

    public StarshipSortRegistry() {
        strategies.put("name", Comparator.comparing(Starship::getName, String.CASE_INSENSITIVE_ORDER));
        strategies.put("created", Comparator.comparing(Starship::getCreated));
    }

    public Comparator<Starship> getComparator(String key, String order) {
        Comparator<Starship> comparator = strategies.getOrDefault(key, strategies.get("name"));
        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }
        return comparator;
    }

    public Set<String> getAvailableKeys() {
        return strategies.keySet();
    }

}
