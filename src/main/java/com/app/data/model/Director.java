package com.app.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Director extends Person {

    @JsonCreator
    public Director(
            @JsonProperty("firstName") String firstname,
            @JsonProperty("lastName") String lastname) {
        super(firstname, lastname);
    }

    @Override
    public String toString() {
        return "Director{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
