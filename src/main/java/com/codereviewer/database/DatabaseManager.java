package com.codereviewer.database;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String DATABASE_PATH =
            Paths.get("reviews.db")
                    .toAbsolutePath()
                    .toString();

    private static final String URL =
            "jdbc:sqlite:" + DATABASE_PATH;

    public static Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(URL);
    }

    public static String getDatabasePath() {
        return DATABASE_PATH;
    }
}