package com.starwars.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeopleResponse {

    private int count;
    private String next;
    private String previous;
    private List<Person> results;
}
