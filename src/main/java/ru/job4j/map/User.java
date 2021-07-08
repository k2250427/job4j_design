package ru.job4j.map;

import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private int children;
    private Calendar birthday;

    public User(String name, int children, Date birthday) {
        this.name = name;
        this.children = children;
        this.birthday = Calendar.getInstance();
        this.birthday.setTime(birthday);
    }

    public static void main(String[] args) {
        Date curDate = new Date();
        User user1 = new User("user", 1, curDate);
        User user2 = new User("user", 1, curDate);
        Map<User, Object> testMap = new HashMap<>();
        testMap.put(user1, new Object());
        testMap.put(user2, new Object());
        System.out.println(testMap);
//-----------
        System.out.println("hashCode user1 = " + user1.hashCode());
         System.out.println("hashCode user2 = " + user2.hashCode());
    }
}

