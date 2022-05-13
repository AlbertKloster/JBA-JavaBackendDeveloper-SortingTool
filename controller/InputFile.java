package sorting.controller;

import sorting.model.DataType;
import sorting.model.RowData;
import sorting.model.SortingType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputFile implements Input {
    @Override
    public RowData getRowData(DataType dataType, SortingType sortingType, String inputFile, String outputFile) {
        File file = new File("./" + inputFile);
        RowData rowData = new RowData(dataType, sortingType, outputFile);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext())
                rowData.add(scanner.nextLine());
        } catch (FileNotFoundException e) {
            System.out.println("No file found: " + inputFile);
        }
        return rowData;
    }
}
