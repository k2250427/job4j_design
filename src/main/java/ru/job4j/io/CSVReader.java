package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class CSVReader {

    private final String path;
    private final String delimiter;
    private final String out;
    private final String filter;
    private final List<String> buffer = new ArrayList<>();

    public CSVReader(String path, String delimiter, String out, String filter) {
        this.path = path;
        this.delimiter = delimiter;
        this.out = out;
        this.filter = filter;
    }

    public void readCSV() {
        String[] curline;
        Set<Integer> columns = new HashSet<>();
        try (BufferedReader inFile = new BufferedReader(new FileReader(path,
                                                            StandardCharsets.UTF_8))) {
            Scanner inScan = new Scanner(inFile);
            curline = inScan.nextLine().split(delimiter);
            for (int i = 0; i < curline.length; i++) {
                if (filter.contains(curline[i])) {
                    columns.add(i);
                }
            }
            while (inScan.hasNext()) {
                String tmp = "";
                curline = inScan.nextLine().split(delimiter);
                for (int i = 0; i < curline.length; i++) {
                    if (columns.contains(i)) {
                        tmp = tmp.concat((tmp.isBlank() ? "" : ";") + curline[i]);
                    }
                }
                buffer.add(tmp);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (out.equals("stdout")) {
            buffer.forEach(System.out::println);
        } else {
            writeBuffer();
        }
    }

    private void writeBuffer() {
        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(out,
                     StandardCharsets.UTF_8))) {
            for (String line:buffer) {
                outFile.write(line + System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        csvReader.readCSV();
    }
}

