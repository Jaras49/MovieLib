package com.app.data.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public final class Movie {

    private final String title;
    private final String director;
    private final String genre;
    private final LocalDate releaseDate;
    private final List<String> actors;

    @JsonCreator
    public Movie(
            @JsonProperty("title") String title,
            @JsonProperty("director") String director,
            @JsonProperty("genre") String genre,
            @JsonProperty("releaseDate") LocalDate releaseDate,
            @JsonProperty("actors") List<String> actors) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.actors = actors;
    }
/**
    private Movie() {
        this.title = null;
        this.director = null;
        this.genre = null;
        this.releaseDate = null;
        this.actors = null;
    }
*/
    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public List<String> getActors() {
        return actors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;
        Movie movie = (Movie) o;
        return Objects.equals(title, movie.title) &&
                Objects.equals(director, movie.director) &&
                Objects.equals(genre, movie.genre) &&
                Objects.equals(releaseDate, movie.releaseDate) &&
                Objects.equals(actors, movie.actors);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title, director, genre, releaseDate, actors);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", director='" + director + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                ", actors=" + actors +
                '}';
    }
}
