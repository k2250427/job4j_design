package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashSet;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private ArrayList<Path> duplicates = new ArrayList<>();
    private HashSet<FileProperty> files = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty curFile = new FileProperty(attrs.size(), file.getFileName().toString());
        if (!files.add(curFile)) {
             duplicates.add(file);
        }
        return super.visitFile(file, attrs);
    }

    public ArrayList<Path> getDuplicates() {
        return this.duplicates;
    }
}