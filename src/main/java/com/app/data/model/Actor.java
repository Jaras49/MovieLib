package com.app.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public final class Actor extends Person {

    @JsonCreator
    public Actor(
            @JsonProperty("firstName") String firstname,
            @JsonProperty("lastName") String lastname) {
        super(firstname, lastname);
    }

    @Override
    public String toString() {
        return "Actor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
