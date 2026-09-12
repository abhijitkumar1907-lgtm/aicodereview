package com.codereviewer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.codereviewer.ai.AIReviewer;
import com.codereviewer.analyzer.CodeReviewer;
import com.codereviewer.model.ReviewResult;
import com.codereviewer.model.codeIssue;

public class Main {

    public static void main(String[] args) {

        try {

            // ==========================================
            // 1. Get Java file to review
            // ==========================================

            Path file = args.length > 0
                    ? Path.of(args[0])
                    : Path.of("src/main/java/com/codereviewer/TestCode.java");

            // Check if file exists
            if (!Files.exists(file)) {

                System.err.println(
                        "Error: " + file + " not found."
                );

                return;
            }

            // ==========================================
            // 2. Read Java source code
            // ==========================================

            String sourceCode = Files.readString(file);

            // Check if file is empty
            if (sourceCode.isBlank()) {

                System.err.println(
                        "Error: "
                                + file.getFileName()
                                + " is empty."
                );

                return;
            }

            // Get file name
            String fileName =
                    file.getFileName().toString();

            // ==========================================
            // 3. Static Analysis
            // ==========================================

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "          AI CODE REVIEWER"
            );

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "File: " + fileName
            );

            System.out.println(
                    "--------------------------------------"
            );

            System.out.println(
                    "Running static analysis..."
            );

            // Create static code reviewer
            CodeReviewer reviewer =
                    new CodeReviewer();

            // Perform static analysis
            ReviewResult result =
                    reviewer.review(
                            sourceCode,
                            fileName
                    );

            // Check result
            if (result == null) {

                System.err.println(
                        "Error: Review returned null."
                );

                return;
            }

            // ==========================================
            // 4. Get Static Analysis Issues
            // ==========================================

            List<codeIssue> staticIssues =
                    result.getIssues();

            System.out.println(
                    "Static analysis completed."
            );

            System.out.println(
                    "Issues found: "
                            + staticIssues.size()
            );

            // ==========================================
            // 5. Display Static Analysis Results
            // ==========================================

            System.out.println(
                    "\n======================================"
            );

            System.out.println(
                    "       STATIC ANALYSIS RESULTS"
            );

            System.out.println(
                    "======================================"
            );

            if (staticIssues.isEmpty()) {

                System.out.println(
                        "No static-analysis issues found."
                );

            } else {

                for (codeIssue issue : staticIssues) {

                    System.out.println(
                            "[" + issue.getSeverity() + "] "
                                    + issue.getRule()
                                    + " | Line: "
                                    + issue.getLine()
                    );

                    System.out.println(
                            "Message: "
                                    + issue.getMessage()
                    );

                    System.out.println(
                            "--------------------------------------"
                    );
                }
            }

            // ==========================================
            // 6. Start AI Reviewer
            // ==========================================

            System.out.println(
                    "\n======================================"
            );

            System.out.println(
                    "       AI REVIEW - QWEN 2.5 CODER"
            );

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "Sending code to Ollama..."
            );

            // Create AI reviewer
            AIReviewer aiReviewer =
                    new AIReviewer();

            // ==========================================
            // 7. Generate AI Review
            // ==========================================

            String aiReview =
                    aiReviewer.review(
                            sourceCode,
                            staticIssues
                    );

            // ==========================================
            // 8. Display AI Review
            // ==========================================

            System.out.println(
                    "\n--------------------------------------"
            );

            System.out.println(
                    "             AI REVIEW"
            );

            System.out.println(
                    "--------------------------------------"
            );

            System.out.println(aiReview);

            System.out.println(
                    "\n======================================"
            );

            System.out.println(
                    "          REVIEW COMPLETED"
            );

            System.out.println(
                    "======================================"
            );

        } catch (IOException e) {

            System.err.println(
                    "Error reading the file:"
            );

            System.err.println(
                    e.getMessage()
            );

        } catch (Exception e) {

            System.err.println(
                    "Error during code analysis:"
            );

            System.err.println(
                    e.getMessage()
            );

            e.printStackTrace();
        }
    }
}