package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {
    private static Config config = new Config("app.properties");

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        config.load();
        Class.forName("org.postgresql.Driver");
        String url = config.value("connection.url");
        String login = config.value("connection.username");
        String password = config.value("connection.password");
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}