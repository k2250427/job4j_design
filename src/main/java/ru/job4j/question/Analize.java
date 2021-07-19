package ru.job4j.question;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        HashMap<Integer, String> curr = new HashMap<>();
        int upd = 0, del = 0;
        Set<Integer> prevkeys = new HashSet<>();
        Set<Integer> curkeys = new HashSet<>();

        for (User user: current) {
            curr.put(user.getId(), user.getName());
            curkeys.add(user.getId());
        }

        for (User user : previous) {
            String name = curr.get(user.getId());
            prevkeys.add(user.getId());
            if (name != null) {
                if (!name.equals(user.getName())) {
                    upd++;
                }
            } else {
                del++;
            }
        }
        curkeys.removeAll(prevkeys);
        return new Info(curkeys.size(), upd, del);
    }
}