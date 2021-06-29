package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ForwardLinked<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        Node<T> node = new Node<>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        node.next = head;
        head = node;
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> p = head;
        head = head.next;
        p.next = null;
        return p.value;
    }

    public boolean revert() {
        if (head == null || head.next == null) {
            return false;
        }
        Node<T> tail = head;
        head = head.next;
        tail.next = null;
        Node<T> p = head;
        do {
             head = head.next;
             p.next = tail;
             tail = p;
             p = head;
        } while (head != null);
        head = tail;
        return true;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
           private Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        private T value;
        private Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}