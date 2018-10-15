package com.app.data.model;

public final class Director extends Person {

    public Director(String firstname, String lastname) {
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
