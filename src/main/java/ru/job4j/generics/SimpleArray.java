package ru.job4j.generics;

import javax.swing.text.html.Option;
import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private ArrayList<Optional<T>> data;
    private int size;
    private int bound = -1;

    public SimpleArray(int size) {
        this.data = new ArrayList<>();
        this.size = size;
    }

    public void add(T model) {
        Objects.checkIndex(data.size(), size);
        data.add(Optional.of(model));
        bound++;
    }

    public T get(int index) {
        Objects.checkIndex(index, bound + 1);
        return data.get(index).get();
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, bound + 1);
        data.set(index, Optional.of(model));
    }

    public void remove(int index) {
        Objects.checkIndex(index, bound + 1);
        data.remove(index);
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
            return data.get(current++).get();
        }

        public boolean hasNext() {
            while (current <= arr.bound && arr.data.get(current).isEmpty()) {
                current++;
            }
            return current <= arr.bound;
        }
    }
}
