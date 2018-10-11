package com.app.data.export;

import com.app.data.model.Movie;

import java.io.IOException;
import java.util.List;

public interface ExportService<T> {

    void export(T address, List<Movie> movies) throws IOException;
}
