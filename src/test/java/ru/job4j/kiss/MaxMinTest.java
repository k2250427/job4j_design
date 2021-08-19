package ru.job4j.kiss;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class MaxMinTest {

    @Test
    public void max() {
        assertThat(MaxMin.max(Arrays.asList(0, 1, 7, 5, 3), Comparator.naturalOrder()), is(7));
    }

    @Test
    public void min() {
        assertThat(MaxMin.min(Arrays.asList(0, 1, 7, 5, 3), Comparator.naturalOrder()), is(0));
    }
}