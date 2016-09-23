package lesson4;

import java.util.*;

/**
 * Created by Ivan on 22/09/16.
 */
public class CollectionUtils {
    public static <T> void addAll(List<? extends T> source, List<? super T> destination) {
        destination.addAll(source);
    }

    public static <T> List<T> newArrayList() {
        return new ArrayList<T>();
    }

    public static <T, V extends T> int indexOf(List<T> source, V value) {
        return source.indexOf(value);
    }

    // метод возвращает:
    // size >= source.size()                --> копию source
    // size <= 0                            --> Collections.EMPTY_LIST
    // size > 0 && size < source.size()-1   --> source.subList(0, size);
    public static <T> List<T> limit(List<T> source, int size) {
        if (size >= source.size())
            size = source.size() - 1;
        return size <= 0 ? Collections.EMPTY_LIST : source.subList(0, size);
    }

    public static <T> void add(List<? super T> source, T o) {
        source.add(o);
    }

    public static <T> void removeAll(List<? super T> removeFrom, List<? extends T> c2) {
        for (T v : c2)
            removeFrom.remove(v);
    }

    public static <T> boolean containsAll(List<? extends T> c1, List<? extends T> c2) {
        return c1.containsAll(c2);
    }

    public static <T> boolean containsAny(List<? extends T> c1, List<? extends T> c2) {
        for (T v : c2)
            if (c1.contains(v))
                return true;
        return false;
    }

    public static <T extends Comparable<T>> List<T> range(List<T> list, T min, T max) {
        Queue<T> q = new PriorityQueue<>();

        for (T v : list)
            if (v.compareTo(min) >= 0 && v.compareTo(max) <= 0)
                q.offer(v);

        return new ArrayList<>(q);
    }

    public static <T, V extends T, Z extends T> List<T> range(List<? extends T> list, V min, Z max, Comparator<? super T> comparator) {
        Queue<T> q = new PriorityQueue<>(comparator);

        for (T v : list)
            if (comparator.compare(v, min) >= 0 && comparator.compare(v, max) <= 0)
                q.offer(v);

        return new ArrayList<>(q);
    }
}
