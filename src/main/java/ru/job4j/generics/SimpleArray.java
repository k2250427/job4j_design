package ru.job4j.generics;

import javax.swing.text.html.Option;
import java.util.*;

public class SimpleArray<T> implements Iterable<T> {
    private Object[] data;
    private int size;
    private int bound;
    private int modCount;

    public SimpleArray(int size) {
        this.data = new Object[size];
        this.size = size;
        this.bound = 0;
        this.modCount = 0;
    }

    public SimpleArray() {
        this(10);
    }

    public void add(T model) {
        //Objects.checkIndex(bound, size);
        if (bound == size) {
            size += 10;
            Object[] tmparr = new Object[size];
            System.arraycopy(data, 0, tmparr, 0, size);
            data = tmparr;
        }
        data[bound++] = model;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, bound);
        return (T) data[index];
    }

    public void set(int index, T model) {
        Objects.checkIndex(index, bound);
        data[index] = model;
        bound++;
        modCount++;
    }

    public void remove(int index) {
        Objects.checkIndex(index, bound);
        System.arraycopy(data, index + 1, data, index, bound - index - 1);
        bound--;
        modCount++;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator(this);
    }

    class SimpleArrayIterator implements Iterator<T> {
        private SimpleArray<T> arr;
        private int current;
        private int modCount;

        SimpleArrayIterator(SimpleArray<T> arr) {
            this.arr = arr;
            this.current = 0;
            this.modCount = arr.modCount;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) data[current++];
        }

        public boolean hasNext() {
            if (modCount != arr.modCount) {
                throw new ConcurrentModificationException();
            }
            return current < arr.bound;
        }
    }
}
