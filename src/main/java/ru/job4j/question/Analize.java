package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        HashMap<Integer, String> curr = new HashMap<>();
        int add, upd = 0, del = 0;

        for (User user: current) {
            curr.put(user.getId(), user.getName());
        }

        for (User user : previous) {
            String name = curr.get(user.getId());
            if (name != null) {
                if (!name.equals(user.getName())) {
                    upd++;
                }
            } else {
                del++;
            }
        }
        add = current.size() + del - previous.size();
        return new Info(add, upd, del);
    }
}