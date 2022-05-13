package sorting.model;

import java.util.*;
import java.util.stream.Collectors;

public class RowData {
    final DataType dataType;
    final SortingType sortingType;
    final String outputFile;
    final List<String> entries = new ArrayList<>();

    public RowData(DataType dataType, SortingType sortingType, String outputFile) {
        this.dataType = Objects.requireNonNullElse(dataType, DataType.WORD);
        this.sortingType = Objects.requireNonNullElse(sortingType, SortingType.NATURAL);
        this.outputFile = outputFile;
    }

    public void add(String entry) {
        entries.add(entry);
    }

    public Data getData() {
        switch (dataType) {
            case LONG: return new Data(dataType, sortingType, outputFile, getDataLong());
            case WORD: return new Data(dataType, sortingType, outputFile, getDataWord());
            case LINE: return new Data(dataType, sortingType, outputFile, getDataLine());
        }
        return null;
    }

    private List<String> getDataLine() {
        return entries;
    }

    private List<String> getDataWord() {
        return entries.stream().map(s -> List.of(s.split("\\s+")))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private List<Long> getDataLong() {
        return getFilteredEntries().stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    private List<String> getFilteredEntries() {
        List<String> filteredEntries = getDataWord();
        ListIterator<String> iterator = filteredEntries.listIterator();
        while (iterator.hasNext())
            checkLong(iterator, iterator.next());
        return filteredEntries;
    }

    private void checkLong(ListIterator<String> iterator, String word) {
        if (isNotLong(word))
            removeNotLong(iterator, word);
    }

    private boolean isNotLong(String word) {
        return !word.matches("-?\\d+");
    }

    private void removeNotLong(ListIterator<String> iterator, String word) {
        System.out.printf("\"%s\" is not a long. It will be skipped.\n", word);
        iterator.remove();
    }
}
