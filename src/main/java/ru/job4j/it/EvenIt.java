package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EvenIt implements Iterator<Integer> {
    private int[] data;
    private int point;

    public EvenIt(int[] data) {
        this.data = data;
        this.point = 0;
    }

    @Override
    public boolean hasNext() {
        boolean rsl = false;
        for (int i = point; i < data.length; i++) {
            if (data[i] % 2 == 0) {
                rsl = true;
                break;
            }
        }
        return rsl;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        while (point < data.length && data[point] % 2 != 0) {
            point++;
        }
        point++;
        return data[point - 1];
    }
}
