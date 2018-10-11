package com.app.data.export;

import com.app.data.model.Movie;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelExportService implements ExportService<Path> {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String EXCEL_FILE_EXTENSION = ".xls";
    private static final String SHEET_NAME = "movies";
    private static final String ACTORS_DELIMITER = ", ";

    private Workbook workbook;

    @Override
    public void export(Path address, List<Movie> movies) throws IOException {
        createWorkBook(movies);

        FileOutputStream fileOutputStream = new FileOutputStream(address.toString() + EXCEL_FILE_EXTENSION);
        workbook.write(fileOutputStream);

        fileOutputStream.close();
        workbook.close();
    }

    private void createWorkBook(List<Movie> movies) {

        workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(SHEET_NAME);

        int columnsNumber = Movie.class.getDeclaredFields().length;

        createHeaderRow(sheet, columnsNumber);
        fillColumnsData(sheet, movies);

        for (int i = 0; i < columnsNumber; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    private void createHeaderRow(Sheet sheet, int columnsNumber) {

        Row headerRow = sheet.createRow(0);

        for (int i = 0; i < columnsNumber; i++) {
            Cell headerCell = headerRow.createCell(i);
            headerCell.setCellValue(Movie.class.getDeclaredFields()[i].getName());
            headerCell.setCellStyle(createHeaderStyle());
        }
    }

    private CellStyle createHeaderStyle() {

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);

        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        return headerCellStyle;
    }

    private void fillColumnsData(Sheet sheet, List<Movie> movies) {

        int rowNumber = 1;
        for (Movie movie : movies) {
            Row row = sheet.createRow(rowNumber++);

            row.createCell(0).setCellValue(movie.getTitle());
            row.createCell(1).setCellValue(movie.getDirector());
            row.createCell(2).setCellValue(movie.getGenre());

            Cell dateCell = row.createCell(3);
            dateCell.setCellValue(convertLocalDateToDate(movie.getReleaseDate()));
            dateCell.setCellStyle(createDateCellStyle());

            row.createCell(4).setCellValue(getActors(movie));
        }
    }

    private CellStyle createDateCellStyle() {
        CellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(DATE_FORMAT));

        return dateCellStyle;
    }

    private Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    private String getActors(Movie movie) {
        return movie.getActors().stream()
                .collect(Collectors.joining(ACTORS_DELIMITER));
    }
}
