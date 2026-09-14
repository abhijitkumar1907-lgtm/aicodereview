package com.codereviewer.model;

import java.util.List;

public class ReviewReport {

    private long reviewId;
    private String fileName;
    private String filePath;
    private String reviewDate;

    private int totalIssues;
    private int criticalIssues;
    private int highIssues;
    private int mediumIssues;
    private int lowIssues;

    private List<codeIssue> staticIssues;

    private String aiReview;

    public long getReviewId() {
        return reviewId;
    }

    public void setReviewId(long reviewId) {
        this.reviewId = reviewId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public int getTotalIssues() {
        return totalIssues;
    }

    public void setTotalIssues(int totalIssues) {
        this.totalIssues = totalIssues;
    }

    public int getCriticalIssues() {
        return criticalIssues;
    }

    public void setCriticalIssues(int criticalIssues) {
        this.criticalIssues = criticalIssues;
    }

    public int getHighIssues() {
        return highIssues;
    }

    public void setHighIssues(int highIssues) {
        this.highIssues = highIssues;
    }

    public int getMediumIssues() {
        return mediumIssues;
    }

    public void setMediumIssues(int mediumIssues) {
        this.mediumIssues = mediumIssues;
    }

    public int getLowIssues() {
        return lowIssues;
    }

    public void setLowIssues(int lowIssues) {
        this.lowIssues = lowIssues;
    }

    public List<codeIssue> getStaticIssues() {
        return staticIssues;
    }

    public void setStaticIssues(List<codeIssue> staticIssues) {
        this.staticIssues = staticIssues;
    }

    public String getAiReview() {
        return aiReview;
    }

    public void setAiReview(String aiReview) {
        this.aiReview = aiReview;
    }
}