package com.codereviewer.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    private static final String DATABASE_DIRECTORY = "data";
    private static final String DATABASE_FILE = "reviews.db";

    private static final String DATABASE_URL =
            "jdbc:sqlite:" + DATABASE_DIRECTORY + "/" + DATABASE_FILE;

    private DatabaseManager() {
        // Prevent object creation
    }

    public static Connection getConnection() throws SQLException {

        createDatabaseDirectory();

        return DriverManager.getConnection(DATABASE_URL);
    }

    private static void createDatabaseDirectory() {

        File directory = new File(DATABASE_DIRECTORY);

        if (!directory.exists()) {
            boolean created = directory.mkdirs();

            if (!created) {
                throw new RuntimeException(
                        "Could not create database directory: "
                                + directory.getAbsolutePath()
                );
            }
        }
    }
}