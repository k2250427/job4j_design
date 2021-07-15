package ru.job4j.map;

import static org.hamcrest.Matchers.is;
import org.junit.Test;

import java.util.Iterator;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutNewKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("Tver", 120));
        assertTrue(map.put("Samara", 400));
        assertTrue(map.put("Petrozavodsk", 80));
    }

    @Test
    public void whenPutNewKeyWithExpand() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("Tver", 120));
        assertTrue(map.put("Samara", 400));
        assertTrue(map.put("Petrozavodsk", 80));
        assertTrue(map.put("Lipetsk", 100));
        assertTrue(map.put("Tula", 200));
        assertTrue(map.put("Kaluga", 180));
        assertTrue(map.put("Ivanovo", 180));
        assertTrue(map.put("Emtsa", 130));
    }

    @Test
    public void whenPutExistingKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("Tver", 120));
        assertTrue(map.put("Samara", 400));
        assertTrue(map.put("Petrozavodsk", 80));
        assertFalse(map.put("Tver", 120));
    }

    @Test
    public void whenGetExistingKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("Tver", 120));
        assertTrue(map.put("Samara", 400));
        assertTrue(map.put("Petrozavodsk", 80));
        assertThat(map.get("Samara"), is(400));
    }

    @Test
    public void whenGetMissingKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("Tver", 120));
        assertTrue(map.put("Samara", 400));
        assertTrue(map.put("Petrozavodsk", 80));
        assertNull(map.get("Moscow"));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenRemoveExistingKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("Tver", 120));
        assertTrue(map.put("Samara", 400));
        assertTrue(map.put("Petrozavodsk", 80));
        assertTrue(map.remove("Samara"));
        Iterator<String> it = map.iterator();
        assertThat(map.get(it.next()), is(120));
        assertThat(map.get(it.next()), is(80));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenRemoveMissingKey() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("Tver", 120));
        assertTrue(map.put("Samara", 400));
        assertTrue(map.put("Petrozavodsk", 80));
        assertFalse(map.remove("Moscow"));
        Iterator<String> it = map.iterator();
        assertThat(map.get(it.next()), is(120));
        assertThat(map.get(it.next()), is(400));
        assertThat(map.get(it.next()), is(80));
        it.next();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIterateOverMap() {
        SimpleMap<String, Integer> map = new SimpleMap<>();
        assertTrue(map.put("Tver", 120));
        assertTrue(map.put("Samara", 400));
        assertTrue(map.put("Petrozavodsk", 80));
        Iterator<String> it = map.iterator();
        assertThat(map.get(it.next()), is(120));
        assertThat(map.get(it.next()), is(400));
        assertThat(map.get(it.next()), is(80));
        it.next();
    }
}