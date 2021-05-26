package ru.job4j.it;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class EvenItTest {

    @Test
    public void whenFirstEvenCallhasNextThenTrue() {
        EvenIt it = new EvenIt(
                new int[] {4, 1, 2}
        );
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenFisrtOddCallhasNextThenTrue() {
        EvenIt it = new EvenIt(
                new int[] {1, 3, 2}
        );
        assertThat(it.hasNext(), is(true));
    }

    @Test
    public void whenCallhasNextThenFalse() {
        EvenIt it = new EvenIt(
                new int[] {3, 1, 5}
        );
        assertThat(it.hasNext(), is(false));
    }

    @Test
    public void whenReadSequence() {
        EvenIt it = new EvenIt(
                new int[] {4, 2, 6}
        );
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(6));
    }

    @Test
    public void whenReadOddEvenSequence() {
        EvenIt it = new EvenIt(
                new int[] {1, 2, 1}
        );
        assertThat(it.next(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNextFromEmpty() {
        EvenIt it = new EvenIt(
                new int[] {}
        );
        it.next();
    }

}