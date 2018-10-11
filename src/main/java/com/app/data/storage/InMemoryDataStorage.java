package com.app.data.storage;

import com.app.data.model.Movie;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class InMemoryDataStorage implements DataStorage {

    private final List<Movie> movies;

    public InMemoryDataStorage() {
        this.movies = new ArrayList<>();
    }

    @Override
    public void persist(List<Movie> movies) {

        this.movies.addAll(movies);
    }

    @Override
    public List<Movie> getData() {
        return movies;
    }

    @Override
    public List<Movie> getByActor(String actor) {
        return movies.stream()
                .filter(movie -> movie.getActors().stream()
                        .anyMatch(n -> n.equalsIgnoreCase(actor)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getByGenre(String genre) {
        return movies.stream()
                .filter(movie -> movie.getGenre().equalsIgnoreCase(genre))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getByDateRange(LocalDate from, LocalDate to) {
        return movies.stream()
                .filter(movie -> movie.getReleaseDate().isAfter(from) && movie.getReleaseDate().isBefore(to))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getByTitle(String title) {
        return movies.stream()
                .filter(movie -> movie.getTitle().equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getByDirector(String director) {
        return movies.stream()
                .filter(movie -> movie.getDirector().equalsIgnoreCase(director))
                .collect(Collectors.toList());
    }
}
