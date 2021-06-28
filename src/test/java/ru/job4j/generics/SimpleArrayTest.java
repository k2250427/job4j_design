package ru.job4j.generics;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleArrayTest {

    @Test
    public void whenAddWhithinBounds() {
        SimpleArray<Integer> arr = new SimpleArray<>(5);
        arr.add(3);
        assertThat(arr.get(0), is(3));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddOverBounds() {
        SimpleArray<Integer> arr = new SimpleArray<>(3);
        arr.add(0);
        arr.add(1);
        arr.add(2);
        arr.add(3);
    }

    @Test
    public void whenSetWhithinBounds() {
        SimpleArray<Integer> arr = new SimpleArray<>(3);
        arr.add(0);
        arr.add(1);
        arr.add(2);
        arr.set(1, 5);
        assertThat(arr.get(1), is(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenSetOverBounds() {
        SimpleArray<Integer> arr = new SimpleArray<>(3);
        arr.add(0);
        arr.add(1);
        arr.add(2);
        arr.set(4, 5);
    }

    @Test
    public void whenRemoveWhithinBounds() {
        SimpleArray<Integer> arr = new SimpleArray<>(3);
        arr.add(0);
        arr.add(1);
        arr.add(2);
        arr.remove(1);
        assertThat(arr.get(1), is(2));
    }

    @Test
    public void whenIterate() {
        SimpleArray<Integer> arr = new SimpleArray<>(3);
        arr.add(0);
        arr.add(null);
        arr.add(2);
        Iterator<Integer> it = arr.iterator();
        assertThat(it.next(), is(0));
        assertNull(it.next());
        assertThat(it.next(), is(2));
    }

    @Test
    public void whenAddThenGet() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.get(0);
        assertThat(rsl, is("first"));
    }

    @Test
    public void whenAddThenIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        SimpleArray<String> array = new SimpleArray<>();
        array.get(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        SimpleArray<String> array = new SimpleArray<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("second");
        it.next();
    }
}