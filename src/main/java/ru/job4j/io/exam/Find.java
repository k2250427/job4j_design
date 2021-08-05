package ru.job4j.io.exam;

import ru.job4j.io.ArgsName;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Find {
    private final Path dir;
    private final String name;
    private final Types type;
    private final String out;

    Find(String dir, String name, String type, String out) {
        this.dir = Path.of(dir);
        switch (type) {
            case ("mask"):
                this.type = Types.MASK;
                this.name = name.toLowerCase();
                break;
            case ("regex"):
                this.type = Types.REGEX;
                this.name = name;
                break;
            default:
                this.type = Types.NAME;
                this.name = name;
                break;
        }
        this.out = out;
    }

    private String maskToRegex(String mask) {
        mask = mask.replace("\\", "\\\\");
        mask = mask.replace("#", "\\#");
        mask = mask.replace(".", "\\.");
        mask = mask.replace("*", ".*");
        mask = mask.replace("?", ".");
        mask = "^".concat(mask).concat("$");
        return mask;
    }

    public void search() throws IOException, PatternSyntaxException {
        Predicate<Path> condition;
        if (type == Types.NAME) {
            condition = p -> name.equals(p.getFileName().toString());
        } else {
            String regex = name;
            if (type == Types.MASK) {
                regex = maskToRegex(name);
            }
            Pattern pattern = Pattern.compile(regex);
            condition = p -> pattern.matcher(p.getFileName().toString()).find();
        }
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(dir, searcher);
        try (PrintWriter outFile = new PrintWriter(
                new BufferedOutputStream(new FileOutputStream(out))
        )) {
            for (Path line: searcher.getFiles()) {
                outFile.println(line.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class SearchFiles extends SimpleFileVisitor<Path> {
        private Predicate<Path> condition;
        private List<Path> fileList = new ArrayList<>();

        public SearchFiles(Predicate<Path> condition) {
            this.condition = condition;
        }

        public List<Path> getFiles() {
            return fileList;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
            if (condition.test(file)) {
                fileList.add(file);
            }
            return CONTINUE;
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = new ArgsName();
        argsName.parse(args);
        String dir = argsName.get("d");
        String name = argsName.get("n");
        String type = argsName.get("t");
        String out = argsName.get("o");

        if (dir == null
                || name == null
                || type == null || !"mask".equals(type) && !"regex".equals(type) && !"name".equals(type)
                || out == null) {
            throw new IllegalArgumentException("Incorrect arguments. Usage: java -jar find.jar "
                                                + "-d=<START_DIR> "
                                                + "-n=<FILE_NAME>|<TEMPLATE> "
                                                + "-t=\"name\"|\"mask\"|\"regex\" "
                                                + "-o=<RESULT_FILE>");
        }
        Find fileFind = new Find(dir, name, type, out);
        fileFind.search();
    }
}