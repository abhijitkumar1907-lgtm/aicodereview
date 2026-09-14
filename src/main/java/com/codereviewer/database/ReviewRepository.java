package com.codereviewer.database;

import com.codereviewer.model.codeIssue;
import com.codereviewer.model.ReviewResult;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class ReviewRepository {

    public long saveReview(
            ReviewResult result,
            String filePath,
            String aiReview) {

        String sql = """
                INSERT INTO reviews
                (
                    file_name,
                    file_path,
                    review_date,
                    total_issues,
                    ai_review
                )
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection connection =
                     DatabaseManager.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(
                             sql,
                             Statement.RETURN_GENERATED_KEYS
                     )) {

            statement.setString(
                    1,
                    result.getFilename()
            );

            statement.setString(
                    2,
                    filePath
            );

            statement.setString(
                    3,
                    LocalDateTime.now().toString()
            );

            statement.setInt(
                    4,
                    result.getIssues().size()
            );

            statement.setString(
                    5,
                    aiReview
            );

            statement.executeUpdate();

            try (ResultSet generatedKeys =
                         statement.getGeneratedKeys()) {

                if (generatedKeys.next()) {

                    long reviewId =
                            generatedKeys.getLong(1);

                    saveIssues(
                            connection,
                            reviewId,
                            result
                    );

                    return reviewId;
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Failed to save review."
            );

            e.printStackTrace();
        }

        return -1;
    }

    private void saveIssues(
            Connection connection,
            long reviewId,
            ReviewResult result)
            throws SQLException {

        String sql = """
                INSERT INTO issues
                (
                    review_id,
                    rule,
                    severity,
                    line,
                    message,
                    suggestion
                )
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            for (codeIssue issue :
                    result.getIssues()) {

                statement.setLong(
                        1,
                        reviewId
                );

                statement.setString(
                        2,
                        issue.getRule()
                );

                statement.setString(
                        3,
                        issue.getSeverity()
                );

                statement.setInt(
                        4,
                        issue.getLine()
                );

                statement.setString(
                        5,
                        issue.getMessage()
                );

                statement.setString(
                        6,
                        issue.getSuggestion()
                );

                statement.addBatch();
            }

            statement.executeBatch();
        }
    }
}