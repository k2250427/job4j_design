package ru.job4j.io;

import org.checkerframework.checker.units.qual.C;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CSVReader {

    private final String path;
    private final String delimiter;
    private final String out;
    private final String filter;

    public CSVReader(String path, String delimiter, String out, String filter) {
        this.path = path;
        this.delimiter = delimiter;
        this.out = out;
        this.filter = filter;
    }

    public List<String> readCSV()throws IOException {
        try (BufferedReader inFile = new BufferedReader(new FileReader(path,
                                                            StandardCharsets.UTF_8));
             BufferedWriter outFile = new BufferedWriter(new FileWriter(out,
                                                            StandardCharsets.UTF_8))) {
            Scanner inScan = new Scanner(inFile).useDelimiter(delimiter);

        }
        return null;
    }

    public static void main(String[] args) {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        String path = argsName.get("p");
        if (path == null) {
            argsName.get("path");
        }
        String delim = argsName.get("d");
        if (delim == null) {
            argsName.get("delimiter");
        }
        String filter = argsName.get("f");
        if (filter == null) {
            argsName.get("filter");
        }
        String out = argsName.get("o");
        if (out == null) {
            argsName.get("out");
        }
        if (path == null || delim == null || filter == null || out == null) {
            throw new IllegalArgumentException("Incorrect arguments. "
                    + "Usage: java -jar csvReader.jar "
                    + "-p[ath]=<INPUT_FILE> "
                    + "-d[elimiter]=<DELIMITER> "
                    + "-f[ilter]=<COLUMN_NAME>[,<COLUMN_NAME>...] "
                    + "-o[ut]=stdout|<OUTPUT_FILE>");
        }
        CSVReader csvReader = new CSVReader(path, delim, out, filter);
    }
}

