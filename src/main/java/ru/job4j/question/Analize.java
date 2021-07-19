package ru.job4j.question;

import java.util.HashMap;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        HashMap<Integer, String> prev = new HashMap<>();
        //HashMap<Integer, String> curr = setToMap(current);
        int add = 0, upd = 0, del = 0;

        for (User user: previous) {
            prev.put(user.getId(), user.getName());
        }

        for (User user : current) {
            String name = user.getName();
            if (name != null) {
                if (!name.equals(prev.get(user.getId()))) {
                    upd++;
                }
            } else {
                del++;
            }
        }
        return new Info(add, upd, del);
    }
}