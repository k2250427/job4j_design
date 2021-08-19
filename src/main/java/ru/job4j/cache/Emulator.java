package ru.job4j.cache;

import java.lang.ref.SoftReference;

public class Emulator {
    private static String initDir = ".\\data\\";
    private static Cache<String, String> cache = new DirFileCache(initDir);

    public static void main(String[] args) {
        System.out.println(cache.get("answers.txt"));
        System.out.println(cache.get("dump.txt"));
        System.out.println(cache.get("log.txt"));
        System.out.println(cache.get("answers.txt"));
        System.out.println(cache.get("log.txt"));
    }
}
