package sorting.controller;

import sorting.model.DataType;
import sorting.model.RowData;
import sorting.model.SortingType;

import java.util.Scanner;

public class InputConsole implements Input {
    @Override
    public RowData getRowData(DataType dataType, SortingType sortingType, String inputFile, String outputFile) {
        Scanner scanner = new Scanner(System.in);
        RowData rowData = new RowData(dataType, sortingType, outputFile);
        while (scanner.hasNext())
            rowData.add(scanner.nextLine());
        return rowData;
    }
}
