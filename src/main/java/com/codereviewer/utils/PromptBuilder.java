package com.codereviewer.utils;

import com.codereviewer.model.codeIssue;

import java.util.List;

public final class PromptBuilder {

    private PromptBuilder() {
    }

    public static String build(
            String sourceCode,
            List<codeIssue> staticIssues) {

        StringBuilder prompt =
                new StringBuilder();

        prompt.append(
                "You are a senior Java code reviewer.\n");

        prompt.append(
                "Review the Java code below. ");

        prompt.append(
                "Focus on correctness, security, ");

        prompt.append(
                "maintainability, performance and clean code.\n");

        prompt.append(
                "Do not invent issues. Give actionable suggestions.\n\n");

        prompt.append(
                "STATIC ANALYSIS FINDINGS:\n");

        if (staticIssues.isEmpty()) {

            prompt.append(
                    "No static-analysis findings.\n");

        } else {

            for (codeIssue issue :
                    staticIssues) {

                prompt.append("- [")
                        .append(issue.getSeverity())
                        .append("] ")
                        .append(issue.getRule())
                        .append(" at line ")
                        .append(issue.getLine())
                        .append(": ")
                        .append(issue.getMessage())
                        .append("\n");
            }
        }

        prompt.append(
                "\nJAVA SOURCE:\n```java\n");

        prompt.append(sourceCode);

        prompt.append(
                "\n```\n");

        prompt.append(
                "\nReturn:\n");

        prompt.append(
                "1. Summary\n");

        prompt.append(
                "2. Critical issues\n");

        prompt.append(
                "3. Suggested fixes\n");

        prompt.append(
                "4. Overall assessment\n");

        return prompt.toString();
    }
}