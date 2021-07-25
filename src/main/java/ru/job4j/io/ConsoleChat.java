package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> answers = new ArrayList<>();
    private boolean verbose = true;
    private String phrase;

    public ConsoleChat(String path, String botAnswers) {
            this.path = path;
            this.botAnswers = botAnswers;
    }

    private String getAnswer() {
        return answers.get((int) (Math.random() * answers.size()));
    }

    public void run() {
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers,
                                                            StandardCharsets.UTF_8));
             BufferedWriter out = new BufferedWriter(new FileWriter(path,
                                                            StandardCharsets.UTF_8))) {
            Scanner inScan = new Scanner(System.in);
            in.lines().forEach(answers::add);
            loggedOut(out, "Привет!");
            while (!OUT.equals(phrase)) {
                System.out.print("> ");
                phrase = inScan.nextLine();
                if (verbose) {
                    if (STOP.equals(phrase)) {
                        verbose = false;
                    } else if (!OUT.equals(phrase)) {
                        loggedOut(out);
                    }
                } else if (CONTINUE.equals(phrase)) {
                    verbose = true;
                }
            }
            loggedOut(out, "Пока!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loggedOut(BufferedWriter out) throws IOException {
        out.write(phrase + System.lineSeparator());
        String answ = getAnswer();
        System.out.println(answ);
        out.write(answ + System.lineSeparator());
    }

    private void loggedOut(BufferedWriter out, String answ) throws IOException {
        System.out.println(answ);
        out.write(answ + System.lineSeparator());
    }

    public static void main(String[] args) {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        String answers = argsName.get("a");
        String log = argsName.get("o");
        if (answers == null || log == null) {
            throw new IllegalArgumentException("Incorrect arguments. "
                    + "Usage: java -jar consoleChat.jar "
                    + "-a=<ANSWERS_FILE> -o=<LOG_FILE>");
        }
        ConsoleChat cc = new ConsoleChat(log, answers);
        cc.run();
    }
}
