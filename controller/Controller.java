package sorting.controller;

import sorting.model.Data;
import sorting.model.DataType;
import sorting.model.RowData;
import sorting.model.SortingType;
import sorting.view.Output;
import sorting.view.OutputConsole;
import sorting.view.OutputFile;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class Controller {

    public static void run(String[] args) {

        Map parameters = getParameters(args);
        DataType dataType = (DataType)parameters.get(DataType.getParameterName());
        SortingType sortingType = (SortingType)parameters.get(SortingType.getParameterName());
        String inputFile = (String)parameters.get("-inputFile");
        String outputFile = (String)parameters.get("-outputFile");

        Input input = inputFile.isBlank() ? new InputConsole() : new InputFile();
        Output output = outputFile.isBlank() ? new OutputConsole() : new OutputFile();
        RowData rowData = input.getRowData(dataType, sortingType, inputFile, outputFile);
        Data data = rowData.getData();
        output.print(data);

    }

    private static Map getParameters(String[] args) {
        Map parameters = initializeParameters();
        ListIterator<String> iterator = List.of(args).listIterator();
        while (iterator.hasNext()) {
            String parameter = iterator.next();
            if (parameters.containsKey(parameter) && iterator.hasNext())
                setParameter(parameters, parameter, iterator);
        }
        return parameters;
    }

    private static void setParameter(Map parameters, String parameter, ListIterator<String> iterator) {
        parameters.put(parameter, getParameterValue(parameter, iterator));
        iterator.previous();
    }

    private static Object getParameterValue(String parameter, ListIterator<String> iterator) {
        if (parameter.equals(DataType.getParameterName()))
            return DataType.getDataType(iterator.next());
        if (parameter.equals(SortingType.getParameterName()))
            return SortingType.getSortingType(iterator.next());
        return iterator.next();
    }

    private static Map initializeParameters() {
        Map parameters = new HashMap();
        parameters.put("-inputFile", "");
        parameters.put("-outputFile", "");
        parameters.put(DataType.getParameterName(), DataType.WORD);
        parameters.put(SortingType.getParameterName(), SortingType.NATURAL);
        return parameters;
    }
}
