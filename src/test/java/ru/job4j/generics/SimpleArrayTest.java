package ru.job4j.generics;

import org.junit.Test;

import java.util.Iterator;

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
        arr.add(1);
        arr.add(2);
        Iterator<Integer> it = arr.iterator();
        assertThat(it.next(), is(0));
        assertThat(it.next(), is(1));
        assertThat(it.next(), is(2));
    }
}