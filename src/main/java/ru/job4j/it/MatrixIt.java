package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class MatrixIt implements Iterator<Integer> {
    private final int[][] data;
    private int row = 0;
    private int column = 0;

    public MatrixIt(int[][] data) {
        this.data = data;
        while (row < data.length && data[row].length == 0) {
            row++;
        }
    }

    @Override
    public boolean hasNext() {
        return row < data.length && column < data[row].length;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Integer rsl = data[row][column++];
        if (column == data[row].length) {
            column = 0;
            row++;
            while (row < data.length && data[row].length == 0) {
                row++;
            }
        }
        return rsl;
    }
}