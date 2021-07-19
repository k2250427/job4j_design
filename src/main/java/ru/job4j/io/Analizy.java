package ru.job4j.io;

import java.io.*;
import java.time.LocalTime;

public class Analizy {
    public static void unavailable(String source, String target) {
        boolean state = false;
        LocalTime start = LocalTime.of(0, 0);
        try (BufferedReader in = new BufferedReader(new FileReader(source));
             BufferedWriter out = new BufferedWriter(new FileWriter(target))) {
            for (String line = in.readLine(); line != null; line = in.readLine()) {
                String[] parts = line.split(" ");
                if (!state && (parts[0].equals("400") || parts[0].equals("500"))) {
                    state = true;
                    start = LocalTime.parse(parts[1]);
                } else if (state && (parts[0].equals("200") || parts[0].equals("300"))) {
                    state = false;
                    out.write(start.toString() + ";" +  LocalTime.parse(parts[1]).toString());
                    out.newLine();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        unavailable("data\\log.txt", "data\\server_unavailable.txt");
    }
}