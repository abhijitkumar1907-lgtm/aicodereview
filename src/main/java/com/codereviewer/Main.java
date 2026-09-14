package com.codereviewer;

import com.codereviewer.ai.AIReviewer;
import com.codereviewer.analyzer.CodeReviewer;
import com.codereviewer.database.DatabaseInitializer;
import com.codereviewer.database.ReviewRepository;
import com.codereviewer.model.codeIssue;
import com.codereviewer.model.ReviewReport;
import com.codereviewer.model.ReviewResult;
import com.codereviewer.utils.JsonReportGenerator;
import com.codereviewer.utils.ReviewReportBuilder;

import java.io.File;
import java.nio.file.Files;

public class Main {

    public static void main(String[] args) {

        // ----------------------------------------
        // 1. Check input
        // ----------------------------------------

        if (args.length != 1) {

            System.out.println(
                    "Usage:"
            );

            System.out.println(
                    "mvn exec:java "
                    + "-Dexec.args=\"sample/Test.java\""
            );

            return;
        }

        String filePath = args[0];

        File file = new File(filePath);

        if (!file.exists()) {

            System.out.println(
                    "File not found: "
                            + file.getAbsolutePath()
            );

            return;
        }

        try {

            // ----------------------------------------
            // 2. Initialize database
            // ----------------------------------------

            DatabaseInitializer.initialize();

            // ----------------------------------------
            // 3. Read source code
            // ----------------------------------------

            String sourceCode =
                    Files.readString(
                            file.toPath()
                    );

            // ----------------------------------------
            // 4. Static analysis
            // ----------------------------------------

            CodeReviewer codeReviewer =
                    new CodeReviewer();

            ReviewResult result =
                    codeReviewer.review(
                            sourceCode,
                            file.getAbsolutePath()
                    );

            // ----------------------------------------
            // 5. Print static analysis
            // ----------------------------------------

            System.out.println();
            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "          STATIC ANALYSIS"
            );

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "File: " + file.getName()
            );

            System.out.println(
                    "Issues: "
                            + result.getIssues().size()
            );

            for (codeIssue issue :
                    result.getIssues()) {

                System.out.println();
                System.out.println(
                        "[" + issue.getSeverity()
                                + "] "
                                + issue.getRule()
                );

                System.out.println(
                        "Line: "
                                + issue.getLine()
                );

                System.out.println(
                        "Message: "
                                + issue.getMessage()
                );

                System.out.println(
                        "Suggestion: "
                                + issue.getSuggestion()
                );
            }

            // ----------------------------------------
            // 6. Ollama AI review
            // ----------------------------------------

            String aiReview =
                    "AI review unavailable.";

            AIReviewer aiReviewer =
        new AIReviewer();

System.out.println();
System.out.println(
        "======================================"
);

System.out.println(
        "             AI REVIEW"
);

System.out.println(
        "======================================"
);

try {

    aiReview =
            aiReviewer.review(
                    sourceCode,
                    result.getIssues()
            );

    System.out.println(
            aiReview
    );

} catch (Exception e) {

    System.out.println(
            "AI review could not be completed."
    );

    System.out.println(
            "Reason: " + e.getMessage()
    );

    System.out.println(
            "Continuing with static analysis results."
    );

    aiReview =
            "AI review unavailable: "
                    + e.getMessage();
}

            // ----------------------------------------
            // 7. Save to database
            // ----------------------------------------

            ReviewRepository repository =
                    new ReviewRepository();

            long reviewId =
                    repository.saveReview(
                            result,
                            file.getAbsolutePath(),
                            aiReview
                    );

            System.out.println();
            System.out.println(
                    "Review saved to database."
            );

            System.out.println(
                    "Review ID: "
                            + reviewId
            );

            // ----------------------------------------
            // 8. Build final report
            // ----------------------------------------

            ReviewReportBuilder builder =
                    new ReviewReportBuilder();

            ReviewReport report =
                    builder.build(
                            result,
                            file.getAbsolutePath(),
                            reviewId,
                            aiReview
                    );

            // ----------------------------------------
            // 9. Generate JSON
            // ----------------------------------------

            JsonReportGenerator generator =
                    new JsonReportGenerator();

            generator.generate(
                    report,
                    "reports/review.json"
            );

            // ----------------------------------------
            // 10. Final message
            // ----------------------------------------

            System.out.println();
            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "       REVIEW COMPLETED"
            );

            System.out.println(
                    "======================================"
            );

            System.out.println(
                    "Database : data/reviews.db"
            );

            System.out.println(
                    "JSON     : reports/review.json"
            );

        } catch (Exception e) {

            System.out.println();
            System.out.println(
                    "Review failed."
            );

            e.printStackTrace();
        }
    }
}