package com.app.data.input;

import com.app.data.model.Movie;

import java.io.IOException;
import java.util.List;

public interface Reader<T> {

    List<Movie> process(T data) throws IOException, InputDataException;
}
