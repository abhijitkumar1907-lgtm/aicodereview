package com.codereviewer.model;

import java.util.Map;

public class ReviewComparison {

    private long previousReviewId;
    private long currentReviewId;

    private int previousTotalIssues;
    private int currentTotalIssues;

    private int issueDifference;

    private Map<String, Integer> previousSeverity;
    private Map<String, Integer> currentSeverity;
    private Map<String, Integer> severityDifference;

    public ReviewComparison() {
    }

    public long getPreviousReviewId() {
        return previousReviewId;
    }

    public void setPreviousReviewId(
            long previousReviewId) {

        this.previousReviewId =
                previousReviewId;
    }

    public long getCurrentReviewId() {
        return currentReviewId;
    }

    public void setCurrentReviewId(
            long currentReviewId) {

        this.currentReviewId =
                currentReviewId;
    }

    public int getPreviousTotalIssues() {
        return previousTotalIssues;
    }

    public void setPreviousTotalIssues(
            int previousTotalIssues) {

        this.previousTotalIssues =
                previousTotalIssues;
    }

    public int getCurrentTotalIssues() {
        return currentTotalIssues;
    }

    public void setCurrentTotalIssues(
            int currentTotalIssues) {

        this.currentTotalIssues =
                currentTotalIssues;
    }

    public int getIssueDifference() {
        return issueDifference;
    }

    public void setIssueDifference(
            int issueDifference) {

        this.issueDifference =
                issueDifference;
    }

    public Map<String, Integer> getPreviousSeverity() {
        return previousSeverity;
    }

    public void setPreviousSeverity(
            Map<String, Integer> previousSeverity) {

        this.previousSeverity =
                previousSeverity;
    }

    public Map<String, Integer> getCurrentSeverity() {
        return currentSeverity;
    }

    public void setCurrentSeverity(
            Map<String, Integer> currentSeverity) {

        this.currentSeverity =
                currentSeverity;
    }

    public Map<String, Integer> getSeverityDifference() {
        return severityDifference;
    }

    public void setSeverityDifference(
            Map<String, Integer> severityDifference) {

        this.severityDifference =
                severityDifference;
    }
}