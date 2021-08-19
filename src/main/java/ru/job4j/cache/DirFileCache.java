package ru.job4j.cache;

import java.io.*;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class DirFileCache extends AbstractCache<String, String>
        implements Cache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String rsl = null;
        try (BufferedReader in = new BufferedReader(new FileReader(cachingDir + key))) {
            StringBuilder text = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                text.append(line);
                text.append(System.lineSeparator());
            }
            rsl = text.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, new SoftReference<>(value));
    }

    @Override
    public String get(String key) {
        String value;
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
}