package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public static void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                new FileOutputStream(target)))) {
            for (File source :sources) {
                zip.putNextEntry(new ZipEntry(source.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(
                                                    new FileInputStream(source))) {
                    zip.write(out.readAllBytes());
                    //zip.closeEntry();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(
                                                            new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
                zip.closeEntry();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        String dir = argsName.get("d");
        String out = argsName.get("o");
        String ext = argsName.get("e");
        if (dir == null || out == null) {
            throw new IllegalArgumentException("Incorrect arguments. Usage: java -jar pack.jar "
                    + "-d=<SOURCE_DIR> [-e=<EXCLUDE_EXTENSION>] -o=<ARCHIVE_NAME>");
        }
        Predicate<Path> pred;
        if (ext != null) {
            pred = p -> !(p.toFile().getName().endsWith(ext));
        } else {
            pred = p -> true;
        }
        List<File> sources = new ArrayList<>();
        try {
            for (Path path: Search.search(Path.of(argsName.get("d")), pred)) {
                sources.add(path.toFile());
            }
            packFiles(sources, new File(out));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}