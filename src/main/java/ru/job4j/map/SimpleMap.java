package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if (count == 0 || get(key) == null) {
            int n = indexFor(hash(Objects.hashCode(key)));
            if (table[n] == null) {
                table[n] = new MapEntry<>(key, value);
                count++;
                modCount++;
                if ((float) count / capacity > LOAD_FACTOR) {
                    expand();
                }
                return true;
            }
        }
        return false;
    }

    private int hash(int hashCode) {
        return Math.abs(hashCode ^ hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash % capacity;
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        for (MapEntry<K, V> el: table) {
            if (el == null) {
                continue;
            }
            int n = indexFor(hash(Objects.hashCode(el.key)));
            newTable[n] = new MapEntry<>(el.key, el.value);
        }
        table = newTable;
    }

    @Override
    public V get(K key) {
        int n = indexFor(hash(Objects.hashCode(key)));
        if (table[n] != null && Objects.equals(table[n].key, key)) {
            return table[n].value;
        }
        return null;
    }

    @Override
    public boolean remove(K key) {
        if (get(key) == null) {
            return false;
        }
        int n = indexFor(hash(Objects.hashCode(key)));
        table[n] = null;
        count--;
        modCount++;
        return true;
    }

    @Override
    public Iterator<K> iterator() {
        return new SimpleMapIterator(this);
    }

    class SimpleMapIterator implements Iterator<K> {

        private SimpleMap<K, V> arr;
        private int current;
        private int modCount;

        SimpleMapIterator(SimpleMap<K, V> arr) {
            this.arr = arr;
            this.current = 0;
            this.modCount = arr.modCount;
        }

        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return arr.table[current++].key;
        }

        public boolean hasNext() {
            if (modCount != arr.modCount) {
                throw new ConcurrentModificationException();
            }
            while (current < arr.capacity && arr.table[current] == null) {
                current++;
            }
            return current < arr.capacity && arr.table[current] != null;
        }

    }

    private static class MapEntry<K, V> {

        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
