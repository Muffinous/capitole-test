package com.starwars.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String name;

    @JsonProperty("birth_year")
    private String birthYear;

    private String gender;
    private String height;
    private String mass;

    @JsonProperty("created")
    private String created;
}
