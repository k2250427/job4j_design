package ru.job4j.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public interface Tree<E> {

    boolean add(E parent, E child);

    Optional<Node<E>> findBy(E value);

    class Node<E> {
        private final E value;
        private final List<Node<E>> children = new ArrayList<>();

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return this.value;
        }

        public void addChild(E value) {
            children.add(new Node<>(value));
        }

        public List<Node<E>> getChildren() {
            return new ArrayList<>(children);
        }
    }
}