package ru.job4j.jdbc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;
    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            String url = properties.getProperty("connection.url");
            String login = properties.getProperty("connection.username");
            String password = properties.getProperty("connection.password");
            connection = DriverManager.getConnection(url, login, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exec(String query) throws Exception {
        try (var statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    public void createTable(String tableName) throws Exception {
        exec(String.format("create table %s()", tableName));
    }

    public void dropTable(String tableName) throws Exception {
        exec(String.format("drop table %s", tableName));
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        exec(String.format("alter table %s add column %s %s", tableName, columnName, type));
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        exec(String.format("alter table %s drop column %s", tableName, columnName));
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
                                                                            throws Exception  {
        exec(String.format("alter table %s rename column %s to %s", tableName, columnName,
                                                                        newColumnName));
    }

    public String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties props = new Properties();
        try (BufferedReader read = new BufferedReader(new FileReader("app.properties"))) {
            props.load(read);
        }
        TableEditor tableEditor = new TableEditor(props);
        tableEditor.createTable("test_table");
        tableEditor.addColumn("test_table", "test_col", "int");
        tableEditor.renameColumn("test_table", "test_col", "new_col");
        tableEditor.dropColumn("test_table", "new_col");
        tableEditor.dropTable("test_table");
        tableEditor.close();
    }
}