package com.app.data.input;

import com.app.data.model.Movie;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FileToObjectReader implements Reader<Path> {

    private static final String JSON = "json";
    private static final String XML = "xml";
    private static final char DOT = '.';

    @Override
    public List<Movie> process(Path data) throws IOException, InputDataException {
        try {
            ObjectMapper mapper = getMapper(data.getFileName().toString())
                    .registerModule(new JavaTimeModule())
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, true)
                    .configure(DeserializationFeature.FAIL_ON_NULL_CREATOR_PROPERTIES, true);

            return Arrays.asList(mapper.readValue(readFile(data), Movie[].class));
        } catch (MismatchedInputException ex) {
            throw new InputDataException(InputDataException.MALFORMED_DATA);
        }
    }

    private String readFile(Path data) throws IOException {

        return Files.readAllLines(data).stream()
                .collect(Collectors.joining("\n"));
    }

    private ObjectMapper getMapper(String filename) throws InputDataException {

        int lastIndex = filename.lastIndexOf(DOT);
        String filenameExtension = filename.substring(lastIndex + 1);

        if (JSON.equalsIgnoreCase(filenameExtension)) {
            return new ObjectMapper();
        } else if (XML.equalsIgnoreCase(filenameExtension)) {
            return new XmlMapper();
        }
        throw new InputDataException(InputDataException.UNSUPPORTED_FILE_FORMAT);
    }
}
