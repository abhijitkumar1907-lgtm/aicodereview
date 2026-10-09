package com.codereviewer.utils;

import com.codereviewer.model.ReviewResult;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.File;

public class JsonReportGeneration {

    private final ObjectMapper objectMapper;
    private final File reportFile;

    public JsonReportGeneration() {

        objectMapper = new ObjectMapper();

        objectMapper.enable(
                SerializationFeature.INDENT_OUTPUT
        );

        reportFile = new File(
                "reports/review.json"
        );
    }

    public void generateReport(
            ReviewResult result) {

        try {

            // =========================================
            // Create reports folder
            // =========================================

            File reportsFolder =
                    reportFile.getParentFile();

            if (reportsFolder != null &&
                    !reportsFolder.exists()) {

                reportsFolder.mkdirs();
            }


            // =========================================
            // Convert current review to JSON
            // =========================================

            JsonNode newReview =
                    objectMapper.valueToTree(result);

            ArrayNode reviewHistory;


            // =========================================
            // Read existing history
            // =========================================

            if (reportFile.exists() &&
                    reportFile.length() > 0) {

                JsonNode existingData =
                        objectMapper.readTree(
                                reportFile
                        );


                // Existing file is already an array
                if (existingData.isArray()) {

                    reviewHistory =
                            (ArrayNode) existingData;
                }

                // Existing file is one review object
                else {

                    reviewHistory =
                            objectMapper.createArrayNode();

                    reviewHistory.add(
                            existingData
                    );
                }

            } else {

                // First review
                reviewHistory =
                        objectMapper.createArrayNode();
            }


            // =========================================
            // Add new review
            // =========================================

            reviewHistory.add(
                    newReview
            );


            // =========================================
            // Save complete history
            // =========================================

            objectMapper.writeValue(
                    reportFile,
                    reviewHistory
            );

            System.out.println(
                    "JSON report generated successfully."
            );

            System.out.println(
                    "Report location: "
                            + reportFile.getAbsolutePath()
            );

        } catch (Exception e) {

            System.out.println(
                    "Failed to generate JSON report."
            );

            e.printStackTrace();
        }
    }
}