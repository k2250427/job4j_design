package ru.job4j.generics;

public class User extends Base {

    public User() {
        super(Long.toString(Math.round(Math.random() * 1000000)));
    }
}
