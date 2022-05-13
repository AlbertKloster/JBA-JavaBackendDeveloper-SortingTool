package sorting.model;

import java.util.Objects;

public class Entry {
    private final Object item;
    private long times;

    public Entry(Object item, long times) {
        this.item = item;
        this.times = times;
    }

    public Object getItem() {
        return item;
    }

    public void increaseTimes() {
        times++;
    }

    public long getTimes() {
        return times;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entry entry = (Entry) o;
        return Objects.equals(item, entry.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item);
    }

}
