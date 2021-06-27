package ru.job4j.generics;

import javax.swing.text.html.Option;
import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int size;
    private int bound;

    public SimpleArray(int size) {
        this.data = new Object[size];
        this.size = size;
        this.bound = 0;
    }

    public void add(T model) {
        Objects.checkIndex(bound, size);
            data[bound++] = model;
    }

    public T get(int index) {
        Objects.checkIndex(index, bound);
        return (T) data[index];
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, bound);
        data[index] = model;
        bound++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, bound);
        for (int i = index + 1; i < bound; i++) {
            data[i - 1] = data[i];
        }
        bound--;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator(this);
    }

    class SimpleArrayIterator implements Iterator<T> {
        private SimpleArray<T> arr;
        private int current;

        SimpleArrayIterator(SimpleArray<T> arr) {
            this.arr = arr;
            this.current = 0;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) data[current++];
        }

        public boolean hasNext() {
            return current < arr.bound;
        }
    }
}
