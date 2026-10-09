package com.codereviewer.model;

public class codeIssue {

    private final String rule;
    private final String severity;
    private final int line;
    private final String message;
    private final String suggestion;

    public codeIssue(
            String rule,
            String severity,
            int line,
            String message,
            String suggestion) {

        this.rule = rule;
        this.severity = severity;
        this.line = line;
        this.message = message;
        this.suggestion = suggestion;
    }

    public String getRule() {
        return rule;
    }

    public String getSeverity() {
        return severity;
    }

    public int getLine() {
        return line;
    }

    public String getMessage() {
        return message;
    }

    public String getSuggestion() {
        return suggestion;
    }

    @Override
    public String toString() {
        return "Rule: " + rule
                + "\nSeverity: " + severity
                + "\nLine: " + line
                + "\nMessage: " + message
                + "\nSuggestion: " + suggestion;
    }
}