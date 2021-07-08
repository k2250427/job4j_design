package ru.job4j.map;

import java.lang.reflect.Field;
import java.util.*;

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

    private static void buckets(HashMap<User, Object> m) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        // Pull out the table.
        Field f = m.getClass().getDeclaredField("table");
        f.setAccessible(true);
        Object[] table = (Object[]) f.get(m);
        int bucket = 0;
        // Walk it.
        for (Object o : table) {
            if (o != null) {
                // At least one in this bucket.
                int count = 1;
                // What's in the `next` field?
                Field nf = o.getClass().getDeclaredField("next");
                nf.setAccessible(true);
                Object n = nf.get(o);
                if (n != null) {
                    do {
                        // Count them.
                        count += 1;
                    } while ((n = nf.get(n)) != null);
                }
                System.out.println("Bucket " + bucket + " contains " + count + " entries");
            }
            bucket += 1;
        }
    }

    @SuppressWarnings("checkstyle:EqualsHashCode")
    @Override
    public int hashCode() {
        return Objects.hash(name, children, birthday);
    }

    public static void main(String[] args) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Date curDate = new Date();
        User user1 = new User("user", 1, curDate);
        User user2 = new User("user", 1, curDate);
        HashMap<User, Object> testMap = new HashMap<>();
        testMap.put(user1, new Object());
        testMap.put(user2, new Object());
        System.out.println(testMap);
//-----------
        System.out.println("hashCode user1 = " + user1.hashCode());
        System.out.println("hashCode user2 = " + user2.hashCode());
        buckets(testMap);
    }


}

