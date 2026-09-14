package com.codereviewer.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {

    private DatabaseInitializer() {
        // Prevent object creation
    }

    public static void initialize() {

        String reviewsTable = """
                CREATE TABLE IF NOT EXISTS reviews (
                    review_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    file_name TEXT NOT NULL,
                    file_path TEXT,
                    review_date TEXT NOT NULL,
                    total_issues INTEGER NOT NULL,
                    ai_review TEXT
                )
                """;

        String issuesTable = """
                CREATE TABLE IF NOT EXISTS issues (
                    issue_id INTEGER PRIMARY KEY AUTOINCREMENT,
                    review_id INTEGER NOT NULL,
                    rule TEXT NOT NULL,
                    severity TEXT,
                    line INTEGER,
                    message TEXT,
                    suggestion TEXT,
                    FOREIGN KEY (review_id)
                        REFERENCES reviews(review_id)
                        ON DELETE CASCADE
                )
                """;

        try (Connection connection =
                     DatabaseManager.getConnection();
             Statement statement =
                     connection.createStatement()) {

            statement.execute(reviewsTable);
            statement.execute(issuesTable);

            System.out.println(
                    "Database initialized successfully."
            );

        } catch (SQLException e) {

            System.out.println(
                    "Failed to initialize database."
            );

            e.printStackTrace();
        }
    }
}