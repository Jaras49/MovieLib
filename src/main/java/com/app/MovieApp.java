package com.app;

import com.app.data.input.FileToObjectReader;
import com.app.data.input.InputDataException;
import com.app.data.input.Reader;
import com.app.data.model.Movie;
import com.app.data.storage.DataStorage;
import com.app.utils.ConsoleInputReader;

import java.io.IOException;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.List;

public class MovieApp {

    private ConsoleInputReader inputReader;
    private DataStorage dataStorage;
    private Reader reader;

    public MovieApp(DataStorage dataStorage, FileToObjectReader reader) {
        inputReader = new ConsoleInputReader();
        this.dataStorage = dataStorage;
        this.reader = reader;
    }

    public void run() {
        controlLoop();
    }

    private void controlLoop() {
        boolean stop = false;

        while (!stop) {
            printOptions();
            try {
                switch (MainOptions.createOption(inputReader.getInt())) {
                    case EXIT:
                        confirm();
                        break;
                    case LOAD_DATA:
                        loadData();
                        break;
                    case FIND_BY_TITLE:
                        findByTitle();
                        break;
                    case FIND_BY_DIRECTOR:
                        findByDirector();
                        break;
                    case FIND_BY_ACTOR:
                        findByActor();
                        break;
                    case FIND_BY_GENRE:
                        findByGenre();
                        break;
                    case FIND_BY_DATE_RANGE:
                        break;
                    case SHOW_ALL:
                        printResults(dataStorage.getData());
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.err.println("Wrong input, try again");
            }
        }
    }

    private void confirm() {

        System.out.println("Are you sure you want to " + MainOptions.EXIT.getName() + " [y/n]");
        if (inputReader.getInput().equalsIgnoreCase("y"))
            System.exit(1);
    }

    private void printOptions() {
        for (MainOptions o : MainOptions.values()) {
            System.out.println(o);
        }
    }

    private void printResults(List<Movie> movies) {
        movies.forEach(System.out::println);
    }

    private void findByTitle() {
        System.out.println("Enter title");
        printResults(dataStorage.getByTitle(inputReader.getInput()));
    }

    private void findByDirector() {
        System.out.println("Enter director");
        printResults(dataStorage.getByDirector(inputReader.getInput()));
    }

    private void findByGenre() {
        System.out.println("Enter genre");
        printResults(dataStorage.getByGenre(inputReader.getInput()));
    }

    private void findByActor() {
        System.out.println("Enter actor");
        printResults(dataStorage.getByActor(inputReader.getInput()));
    }

    private void loadData() {
        System.out.println("Enter file path");
        //C:\Users\Jaras\Desktop\programowanie\movieApp\src\main\resources\movies.json
        try {
            String path = inputReader.getInput();
            List<Movie> movies = reader.process(Paths.get(path));
            dataStorage.persist(movies);
        } catch (InputDataException ex) {
            System.err.println(ex.getMessage());
        } catch (NullPointerException | InvalidPathException ex) {
            System.err.println("Invalid file path");
        } catch (IOException ex) {
            System.err.println("Failed to load data");
        }
    }
}
