package sorting.model;

import java.util.InputMismatchException;
import java.util.Locale;

public enum SortingType {
    NATURAL("natural"), BYCOUNT("byCount");

    public final String type;

    SortingType(String type) {
        this.type = type;
    }

    public static SortingType getSortingType(String input) {
        for (SortingType sortingType : SortingType.values()) {
            if (sortingType.name().equals(input.toUpperCase(Locale.ROOT)))
                return sortingType;
        }
        throw new InputMismatchException();
    }

    public static String getParameterName() {
        return "-sortingType";
    }
}
