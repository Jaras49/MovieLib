package com.app;

import com.app.data.export.ExcelExportService;
import com.app.data.input.FileToObjectReader;
import com.app.data.storage.InMemoryDataStorage;

public class Main {

    public static void main(String[] args) {

        new MovieApp(new InMemoryDataStorage(), new FileToObjectReader(), new ExcelExportService())
                .run();
    }
}
