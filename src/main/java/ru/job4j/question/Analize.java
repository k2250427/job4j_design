package ru.job4j.question;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        HashMap<Integer, String> prev = setToMap(previous);
        HashMap<Integer, String> curr = setToMap(current);
        int add = 0, upd = 0, del = 0;
        for (Integer id : prev.keySet()) {
            String name = curr.get(id);
            if (name != null) {
                if (!name.equals(prev.get(id))) {
                    upd++;
                }
            } else {
                del++;
            }
        }
        for (Integer id : curr.keySet()) {
            if (prev.get(id) == null) {
                add++;
            }
        }
        return new Info(add, upd, del);
    }

    private static HashMap<Integer, String> setToMap(Set<User> userSet) {
        HashMap<Integer, String> rsl = new HashMap<>();
        for (User user: userSet) {
            rsl.put(user.getId(), user.getName());
        }
        return rsl;
    }

}