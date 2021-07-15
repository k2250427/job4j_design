package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

public class SimpleTree<E> implements Tree<E> {
    private Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> opNode = findBy(child);
        if (opNode.isPresent()) {
            return false;
        }
        opNode = findBy(parent);
        if (opNode.isPresent()) {
            Node<E> node = opNode.get();
            for (Node<E> ch: node.getChildren()) {
                if (Objects.equals(ch.getValue(), child)) {
                    return false;
                }
            }
            node.addChild(child);
            return true;
        }
        return false;
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.getChildren());
        }
        return rsl;
    }

    public boolean isBinary() {
        Optional<Node<E>> rsl = findByPredicate(el -> el.getChildren().size() > 2);
        return rsl.isEmpty();
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(el -> el.getValue().equals(value));
    }
}
