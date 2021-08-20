package ru.job4j.template;

import org.junit.Test;

import java.security.InvalidParameterException;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GeneratorTest {

    @Test
    public void whenTemplateCorrect() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        Generator gen = new SampleGenerator();
        String rsl = gen.produce("I am a ${name}, Who are ${subject}? ", args);
        assertThat(rsl, is("I am a Petr Arsentev, Who are you? "));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenKeyMissing() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        Generator gen = new SampleGenerator();
        String rsl = gen.produce("I am a ${name}, Who are ${subject}? ", args);
    }

    @Test(expected = InvalidParameterException.class)
    public void whenKeyRedundant() {
        Map<String, String> args = new HashMap<>();
        args.put("name", "Petr Arsentev");
        args.put("subject", "you");
        args.put("day", "Sunday");
        Generator gen = new SampleGenerator();
        String rsl = gen.produce("I am a ${name}, Who are ${subject}? ", args);
    }
}