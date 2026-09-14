package com.codereviewer.utils;

import com.codereviewer.model.codeIssue;
import com.codereviewer.model.ReviewReport;
import com.codereviewer.model.ReviewResult;

import java.time.LocalDateTime;

public class ReviewReportBuilder {

    public ReviewReport build(
            ReviewResult result,
            String filePath,
            long reviewId,
            String aiReview) {

        ReviewReport report =
                new ReviewReport();

        report.setReviewId(reviewId);

        report.setFileName(
                result.getFilename()
        );

        report.setFilePath(
                filePath
        );

        report.setReviewDate(
                LocalDateTime.now().toString()
        );

        report.setTotalIssues(
                result.getIssues().size()
        );

        int critical = 0;
        int high = 0;
        int medium = 0;
        int low = 0;

        for (codeIssue issue :
                result.getIssues()) {

            String severity =
                    issue.getSeverity();

            if (severity == null) {
                continue;
            }

            switch (severity.toUpperCase()) {

                case "CRITICAL":
                    critical++;
                    break;

                case "HIGH":
                    high++;
                    break;

                case "MEDIUM":
                    medium++;
                    break;

                case "LOW":
                    low++;
                    break;

                default:
                    break;
            }
        }

        report.setCriticalIssues(
                critical
        );

        report.setHighIssues(
                high
        );

        report.setMediumIssues(
                medium
        );

        report.setLowIssues(
                low
        );

        report.setStaticIssues(
                result.getIssues()
        );

        report.setAiReview(
                aiReview
        );

        return report;
    }
}