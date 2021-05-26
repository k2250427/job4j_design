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
        while (point < data.length && data[point] % 2 != 0) {
            point++;
        }
        return point < data.length && data[point] % 2 == 0;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return data[point++];
    }
}
