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
    private List<String> log = new ArrayList<>();
    private boolean verbose = true;
    private String phrase;

    public ConsoleChat(String path, String botAnswers) {
            this.path = path;
            this.botAnswers = botAnswers;
    }

    private String getAnswer() {
        return answers.get((int) (Math.random() * answers.size()));
    }

    private void readAnswers() {
        try (BufferedReader in = new BufferedReader(new FileReader(botAnswers,
                StandardCharsets.UTF_8))) {
            in.lines().forEach(answers::add);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeLog() {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path,
                     StandardCharsets.UTF_8))) {
            for (String msg: log) {
                out.write(msg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        readAnswers();
        Scanner inScan = new Scanner(System.in);
        loggedOut("Привет!");
        while (!OUT.equals(phrase)) {
            System.out.print("> ");
            phrase = inScan.nextLine();
            if (verbose) {
                if (STOP.equals(phrase)) {
                    verbose = false;
                } else if (!OUT.equals(phrase)) {
                    loggedOut();
                }
            } else if (CONTINUE.equals(phrase)) {
                verbose = true;
            }
        }
        loggedOut("Пока!");
        writeLog();
    }

    private void loggedOut() {
        log.add(phrase + System.lineSeparator());
        String answ = getAnswer();
        System.out.println(answ);
        log.add(answ + System.lineSeparator());
    }

    private void loggedOut(String answ) {
        System.out.println(answ);
        log.add(answ + System.lineSeparator());
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
