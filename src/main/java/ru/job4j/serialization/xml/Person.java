package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "person")
class Person {
    @XmlElement
    private String name = "";
    @XmlElement
    private boolean sex = false;
    @XmlElement
    private int age = 0;

    public Person() {

    }

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
}

