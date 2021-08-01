package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.file.Files;

public class Example implements Serializable {
    private static final long serialVersionUID = 1L;
    private final int zipCode;
    private final String phone;
    private final Person person;

    public Example(int zipCode, String phone, String name, boolean sex, int age) {
        this.zipCode = zipCode;
        this.phone = phone;
        this.person = new Person(name, sex, age);
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Contact{"
                + "zipCode=" + zipCode
                + ", phone='" + phone + '\''
                + ", person=" + person
                + '}';
    }

    class Person {
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
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        final Example contact = new Example(123456,
                                        "+7 (111) 111-11-11",
                                        "Steve Black",
                                        true,
                                        39);
        final Gson gson = new GsonBuilder().create();
        final String contactJson = gson.toJson(contact);
        System.out.println(contactJson);
        final Example contactMod = gson.fromJson(contactJson, Example.class);
        System.out.println(contactMod);
    }
}