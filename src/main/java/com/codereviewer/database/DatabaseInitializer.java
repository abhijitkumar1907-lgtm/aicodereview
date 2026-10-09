package com.codereviewer.database;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.Statement;

@Component
public class DatabaseInitializer {

    @PostConstruct
    public void initializeDatabase() {

        String createReviewsTable = """
                CREATE TABLE IF NOT EXISTS reviews (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    file_name TEXT NOT NULL,
                    file_path TEXT,
                    review_date TEXT,
                    total_issues INTEGER,
                    ai_review TEXT
                )
                """;

        String createIssuesTable = """
                CREATE TABLE IF NOT EXISTS issues (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    review_id INTEGER NOT NULL,
                    rule TEXT,
                    severity TEXT,
                    line INTEGER,
                    message TEXT,
                    suggestion TEXT,
                    FOREIGN KEY (review_id)
                        REFERENCES reviews(id)
                )
                """;

        try (Connection connection =
                     DatabaseManager.getConnection();
             Statement statement =
                     connection.createStatement()) {

            statement.execute(createReviewsTable);
            statement.execute(createIssuesTable);

            System.out.println(
                    "Database initialized successfully."
            );

            System.out.println(
                    "Database location: "
                            + DatabaseManager.getDatabasePath()
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to initialize database."
            );

            e.printStackTrace();
        }
    }
}