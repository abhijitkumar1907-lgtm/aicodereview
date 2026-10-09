package com.codereviewer;

import com.codereviewer.ai.AIReviewer;
import com.codereviewer.analyzer.CodeReviewer;
import com.codereviewer.database.ReviewRepository;
import com.codereviewer.model.ReviewResult;
import com.codereviewer.utils.JsonReportGeneration;

public class Main {

    public static void main(String[] args) {

        // Sample Java source code
        String sourceCode = """
                public class TestCode {

                    public static void main(String[] args) {

                        int unusedVariable = 10;

                        for (int i = 0; i < 10; i++) {
                            System.out.println(i);
                        }
                    }

                    public void longMethod() {

                        int a = 10;
                        int b = 20;
                        int c = 30;
                        int d = 40;
                        int e = 50;

                        System.out.println(a);
                        System.out.println(b);
                        System.out.println(c);
                        System.out.println(d);
                        System.out.println(e);
                    }
                }
                """;

        String filename = "TestCode.java";

        try {

            // -----------------------------------------
            // 1. Static Code Analysis
            // -----------------------------------------

            CodeReviewer codeReviewer =
                    new CodeReviewer();

            ReviewResult result =
                    codeReviewer.review(
                            sourceCode,
                            filename
                    );

            System.out.println(
                    "\nStatic analysis completed."
            );

            System.out.println(
                    "Total issues found: "
                            + result.getIssues().size()
            );


            // -----------------------------------------
            // 2. AI Code Review
            // -----------------------------------------

            AIReviewer aiReviewer =
                    new AIReviewer();

            String aiReview =
                    aiReviewer.review(
                            sourceCode,
                            result.getIssues()
                    );

            result.setAiReview(aiReview);

            System.out.println(
                    "\nAI review completed."
            );


            // -----------------------------------------
            // 3. Save Review to Database
            // -----------------------------------------

            ReviewRepository reviewRepository =
                    new ReviewRepository();

            reviewRepository.saveReview(
                    result,
                    filename,
                    aiReview
            );

            System.out.println(
                    "\nReview saved to database."
            );


            // -----------------------------------------
            // 4. Generate JSON Report
            // -----------------------------------------

            JsonReportGeneration jsonReportGeneration =
                    new JsonReportGeneration();

            jsonReportGeneration.generateReport(
                    result
            );

            System.out.println(
                    "\nJSON report generated successfully."
            );

            System.out.println(
                    "Location: reports/review.json"
            );


            // -----------------------------------------
            // 5. Display Result
            // -----------------------------------------

            System.out.println(
                    "\n================================="
            );

            System.out.println(
                    "       CODE REVIEW RESULT"
            );

            System.out.println(
                    "================================="
            );

            System.out.println(
                    "File: "
                            + result.getFilename()
            );

            System.out.println(
                    "Issues: "
                            + result.getIssues().size()
            );

            System.out.println(
                    "Review Date: "
                            + result.getReviewDate()
            );

            System.out.println(
                    "================================="
            );

        } catch (Exception e) {

            System.out.println(
                    "\nError while running code review:"
            );

            e.printStackTrace();
        }
    }
}