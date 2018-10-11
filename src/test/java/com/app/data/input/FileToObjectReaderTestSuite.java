package com.app.data.input;

import com.app.data.model.Movie;
import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FileToObjectReaderTestSuite {

    private static final String MOVIES_JSON_FILENAME = "movies.JSON";
    private static final String MOVIES_XML_FILENAME = "movies.xml";

    private FileToObjectReader reader;

    @Before
    public void setUp() throws Exception {
        reader = new FileToObjectReader();
    }

    @Test
    public void shouldReadJsonFile() throws URISyntaxException, Exception {

        //Given
        URI uri = this.getClass().getClassLoader().getResource(MOVIES_JSON_FILENAME).toURI();
        Movie expectedMovie = new Movie("Game of Thrones", "Andrzej", "Fantasy",
                LocalDate.of(2015, 4, 15), Arrays.asList("Bolton", "Stark"));

        //When
        List<Movie> movies = reader.process(Paths.get(uri));

        //Then
        assertEquals(2, movies.size());
        assertEquals(expectedMovie, movies.get(0));
    }

    @Test
    public void shouldReadXmlFile() throws URISyntaxException,Exception {

        //Given
        URI uri = this.getClass().getClassLoader().getResource(MOVIES_XML_FILENAME).toURI();
        Movie expectedMovie = new Movie("Hello World", "Pro", "Political",
                LocalDate.of(2018, 1, 12), Arrays.asList("Jarek", "Marcin"));

        //When
        List<Movie> movies = reader.process(Paths.get(uri));

        //Then
        assertEquals(3, movies.size());
        assertEquals(expectedMovie, movies.get(2));
    }
}