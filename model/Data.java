package sorting.model;

import java.util.*;

public class Data {
    final DataType dataType;
    final SortingType sortingType;
    final String outputFile;
    final List data;

    public Data(DataType dataType, SortingType sortingType, String outputFile, List data) {
        this.dataType = dataType;
        this.sortingType = sortingType;
        this.outputFile = outputFile;
        this.data = data;
        sortNatural();
    }

    public String getOutputFile() {
        return outputFile;
    }

    private void sortNatural() {
        Collections.sort(data);
    }

    public String getMessage() {
        if (sortingType == SortingType.NATURAL)
            return getMessage(data);
        else
            return getMessage(getDataByCount(data));
    }

    private String getMessage(List data) {
        StringBuilder message = new StringBuilder();
        message
                .append("Total ")
                .append(dataType.type)
                .append("s: ")
                .append(this.data.size())
                .append("\n")
                .append("Sorted data:");
        data.forEach(entry -> {
            message.append((dataType.equals(DataType.LINE) ? "\n" : " "))
                            .append(entry);
        });
        return message.toString();
    }

    private String getMessage(Map data) {
        StringBuilder message = new StringBuilder();
        message
                .append("Total ")
                .append(dataType.type)
                .append("s: ")
                .append(this.data.size())
                .append(".\n");

        int totalNumbers = this.data.size();
        data.forEach((k, v) -> message.append(
                String.format(
                        (dataType.equals(DataType.LONG) ? "%d" : "%s") + ": %d time(s), %.0f%%\n",
                        k,
                        v, 100.0 * (long)v / totalNumbers)
                )
        );
        return message.toString();
    }

    private Map getDataByCount(List entries) {
        return sortByCount(getCountList(entries));
    }

    private List<Entry> getCountList(List items) {
        List<Entry> countList = new ArrayList<>();
        items.forEach(item -> addItemToCountList(item, countList));
        return countList;
    }

    private void addItemToCountList(Object item, List<Entry> countList) {
            Entry entry = new Entry(item, 1);
            int index = countList.indexOf(entry);
            if (index < 0)
                countList.add(entry);
            else
                countList.get(index).increaseTimes();
    }

    private Map sortByCount(List<Entry> countList) {
        Map sortedMap = new LinkedHashMap();
        countList.stream()
                .sorted(Comparator.comparingLong(Entry::getTimes))
                .forEachOrdered(entry -> sortedMap.put(entry.getItem(), entry.getTimes()));
        return sortedMap;
    }

}
