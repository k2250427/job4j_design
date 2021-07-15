package ru.job4j.io;

import org.hamcrest.Matchers;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/without_comment.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1"),is("value1"));
        assertThat(config.value("key2"),is("value2"));
        assertThat(config.value("key3"),is("value3"));
        assertThat(config.value("surname"), is(Matchers.nullValue()));
    }

    @Test
    public void whenPairWithCommentAndEmptyLine() {
        String path = "./data/with_comments_and_empty_lines.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("key1"),is("value1"));
        assertThat(config.value("key2"),is("value2"));
        assertThat(config.value("key3"),is("value3"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void whenPairWithInvalidFormat() {
        String path = "./data/with_invalid_format.properties";
        Config config = new Config(path);
        config.load();
    }
}