package ru.job4j.colllection.list;

public interface List<E> extends Iterable<E> {
    void add(E value);

    E get(int index);
}
