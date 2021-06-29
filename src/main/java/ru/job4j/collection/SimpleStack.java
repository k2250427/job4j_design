package ru.job4j.collection;

import java.util.Iterator;

public class SimpleStack<T> {
    private ForwardLinked<T> linked = new ForwardLinked<>();

    public T pop() {
        return linked.deleteFirst();
    }

    public void push(T value) {
        linked.addFirst(value);
    }

    public boolean isEmpty() {
        Iterator<T> it = linked.iterator();
        return !it.hasNext();
    }

}
