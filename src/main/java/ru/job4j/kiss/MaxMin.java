package ru.job4j.kiss;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MaxMin {

    public static <T> T max(List<T> value, Comparator<T> comparator) {
        return getResult(value, comparator, -1);
    }

    public static <T> T min(List<T> value, Comparator<T> comparator) {
        return getResult(value, comparator, 1);
    }

    private static <T> T getResult(List<T> value, Comparator<T> comparator, int expected) {
        T rsl = value.get(0);
        for (T item :value) {
            if (comparator.compare(rsl, item) == expected) {
                rsl = item;
            }
        }
        return rsl;
    }
}
