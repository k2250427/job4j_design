package ru.job4j.colllection.list;

import ru.job4j.generics.SimpleArray;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {
    private Node<E> first;
    private Node<E> last;
    private int size;

    public SimpleLinkedList() {
        this.size = 0;
    }

    @Override
    public void add(E value) {
        final Node<E> l = last;
        final Node<E> newNode = new Node<>(l, value, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> p = first;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new SimpleLinkedListIterator(this);
    }

    class SimpleLinkedListIterator implements Iterator<E> {
        private SimpleLinkedList<E> arr;
        private Node<E> current;
        private int modCount;

        SimpleLinkedListIterator(SimpleLinkedList<E> arr) {
            this.arr = arr;
            this.current = arr.first;
            this.modCount = arr.size;
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E value = current.item;
            current = current.next;
            return value;
        }

        public boolean hasNext() {
            if (modCount != arr.size) {
                throw new ConcurrentModificationException();
            }
            return current != null;
        }
    }

    private static class Node<E> {
        private E item;
        private Node<E> next;
        private Node<E> prev;

        Node(Node<E> prev, E element, Node<E> next) {
            this.item = element;
            this.next = next;
            this.prev = prev;
        }
    }
}
