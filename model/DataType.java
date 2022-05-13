package sorting.model;

import java.util.InputMismatchException;
import java.util.Locale;

public enum DataType {
    LONG("number"), LINE("line"), WORD("word");

    public final String type;

    DataType(String type) {
        this.type = type;
    }

    public static DataType getDataType(String input) {
        for (DataType dataType : DataType.values()) {
            if (dataType.name().equals(input.toUpperCase(Locale.ROOT)))
                return dataType;
        }
        throw new InputMismatchException();
    }

    public static String getParameterName() {
        return "-dataType";
    }

}
