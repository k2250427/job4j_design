package ru.job4j.serialization.json;

public class Person {
    private final String name;
    private final boolean sex;
    private final int age;

    public Person(String name, boolean sex, int age) {
        this.name = name;
        this.sex = sex;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{"
                + "name=" + name
                + ", sex=" + sex
                + ", age=" + age
                + '}';
    }

    public String getName() {
        return name;
    }

    public boolean isSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}