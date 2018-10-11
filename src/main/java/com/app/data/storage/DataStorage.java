package com.app.data.storage;

import com.app.data.model.Movie;

import java.time.LocalDate;
import java.util.List;

public interface DataStorage {

    void persist(List<Movie> movies);
    List<Movie> getData();
    List<Movie> getByActor(String actor);
    List<Movie> getByGenre(String genre);
    List<Movie> getByDateRange(LocalDate from, LocalDate to);
    List<Movie> getByTitle(String title);
    List<Movie> getByDirector(String director);
}
