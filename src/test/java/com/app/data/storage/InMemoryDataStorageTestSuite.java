package com.app.data.storage;

import com.app.data.model.Actor;
import com.app.data.model.Director;
import com.app.data.model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class InMemoryDataStorageTestSuite {

    private InMemoryDataStorage dataStorage;

    @Before
    public void setUp() throws Exception {

        //Given
        dataStorage = new InMemoryDataStorage();
        dataStorage.persist(Arrays.asList(
                new Movie("xxx", new Director("Andrzej", "Nowak"), "action", LocalDate.of(1992, 8, 1),
                        Arrays.asList(new Actor("x", "y"), new Actor("y", "x"))),
                new Movie("xxx", new Director("Andrzej", "Nowak"), "action", LocalDate.of(1998, 8, 1),
                        Arrays.asList(new Actor("z", "z"), new Actor("a", "a"))),
                new Movie("xxx", new Director("Andrzej", "Nowak"), "fantasy", LocalDate.of(2000, 8, 1),
                        Arrays.asList(new Actor("b", "b"), new Actor("c", "c"))),
                new Movie("xxx", new Director("Marcin", "Kowal"), "fantasy", LocalDate.of(2005, 8, 1),
                        Arrays.asList(new Actor("b", "b"), new Actor("c", "c"))),
                new Movie("zzz", new Director("Marcin", "Kowal"), "fantasy", LocalDate.of(2015, 8, 1),
                        Arrays.asList(new Actor("b", "b"), new Actor("c", "c")))
        ));
    }

    @Test
    public void shouldGetByActor() {

        //When
        List<Movie> byActor1 = dataStorage.getByActor("b");
        List<Movie> byActor2 = dataStorage.getByActor("C");
        List<Movie> byActor3 = dataStorage.getByActor("X");

        //Then
        assertEquals(3, byActor1.size());
        assertEquals(3, byActor2.size());
        assertEquals(1, byActor3.size());
    }

    @Test
    public void shouldGetByGenre() {

        //When
        List<Movie> byGenre1 = dataStorage.getByGenre("Action");
        List<Movie> byGenre2 = dataStorage.getByGenre("FANTASY");

        //Then
        assertEquals(2, byGenre1.size());
        assertEquals(3, byGenre2.size());
    }

    @Test
    public void shouldGetByDateRange() {

        //When
        List<Movie> byDateRange1 = dataStorage.getByDateRange(LocalDate.of(2000, 9, 1), LocalDate.of(2018, 10, 11));
        List<Movie> byDateRange2 = dataStorage.getByDateRange(LocalDate.of(1991, 1, 1), LocalDate.of(2006, 1, 1));

        //Then
        assertEquals(2, byDateRange1.size());
        assertEquals(4, byDateRange2.size());
    }

    @Test
    public void shouldGetByTitle() {

        //When
        List<Movie> byTitle1 = dataStorage.getByTitle("xXx");
        List<Movie> byTitle2 = dataStorage.getByTitle("ZZZ");

        //Then
        assertEquals(4, byTitle1.size());
        assertEquals(1, byTitle2.size());
    }

    @Test
    public void shouldGetDirector() {

        //When
        List<Movie> byDirector1 = dataStorage.getByDirector("NoWak");
        List<Movie> byDirector2 = dataStorage.getByDirector("kowal");

        //Then
        assertEquals(3, byDirector1.size());
        assertEquals(2, byDirector2.size());
    }
}