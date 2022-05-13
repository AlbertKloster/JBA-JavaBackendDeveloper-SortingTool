package sorting.controller;

import sorting.model.DataType;
import sorting.model.RowData;
import sorting.model.SortingType;

public interface Input {
    RowData getRowData(DataType dataType, SortingType sortingType, String inputFile, String outputFile);
}
