package com.codereviewer.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReviewResult {

    private final String filename;

    private final List<codeIssue> issues =
            new ArrayList<>();

    private String aiReview;

    private String filePath;

    private String reviewDate;

    public ReviewResult(String filename) {
        this.filename = filename;
        this.reviewDate = LocalDateTime.now().toString();
    }

    // =========================================================
    // ADD ISSUE
    // =========================================================

    public void addIssue(codeIssue issue) {
        issues.add(issue);
    }

    // =========================================================
    // GET FILENAME
    // =========================================================

    public String getFilename() {
        return filename;
    }

    // =========================================================
    // GET ISSUES
    // =========================================================

    public List<codeIssue> getIssues() {
        return Collections.unmodifiableList(issues);
    }

    // =========================================================
    // AI REVIEW
    // =========================================================

    public void setAiReview(String aiReview) {
        this.aiReview = aiReview;
    }

    public String getAiReview() {
        return aiReview;
    }

    // =========================================================
    // FILE PATH
    // =========================================================

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    // =========================================================
    // REVIEW DATE
    // =========================================================

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }
}