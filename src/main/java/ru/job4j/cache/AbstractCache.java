package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractCache<K, V> implements Cache<K, V> {

    @SuppressWarnings("checkstyle:VisibilityModifier")
    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        V value;
        if (cache.containsKey(key)) {
            value = cache.get(key).get();
            if (value != null) {
                System.out.println("Value from cache:");
                return value;
            }
        }
        value = load(key);
        cache.put(key, new SoftReference<>(value));
        return value;
    }

    protected abstract V load(K key);

}
