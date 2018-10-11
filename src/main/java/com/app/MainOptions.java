package com.app;

public enum MainOptions {
    EXIT(0, "Exit program"),
    LOAD_DATA(1, "Load data"),
    FIND_BY_TITLE(2, "Find by title"),
    FIND_BY_DIRECTOR(3, "Find by director"),
    FIND_BY_ACTOR(4, "Find by actor"),
    FIND_BY_GENRE(5, "Find by Genre"),
    FIND_BY_DATE_RANGE (6, "Find by date"),
    SHOW_ALL(7, "Show all movies");

    private int value;
    private String description;

    MainOptions(int value, String description){
        this.value = value;
        this.description = description;
    }

    public static MainOptions createOption(int option){

        return MainOptions.values()[option];
    }

    @Override
    public String toString(){
        return value + " - " + description;
    }

    public String getName(){
        return name();
    }
}
