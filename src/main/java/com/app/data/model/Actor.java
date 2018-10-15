package com.app.data.model;

public final class Actor extends Person {

    public Actor(String firstname, String lastname) {
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
