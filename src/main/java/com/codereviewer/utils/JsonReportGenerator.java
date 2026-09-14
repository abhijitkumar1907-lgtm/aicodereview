package com.codereviewer.utils;

import com.codereviewer.model.ReviewReport;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;

public class JsonReportGenerator {

    private final ObjectMapper objectMapper;

    public JsonReportGenerator() {

        objectMapper = new ObjectMapper();

        objectMapper.enable(
                SerializationFeature.INDENT_OUTPUT
        );
    }

    public void generate(
            ReviewReport report,
            String outputPath) throws Exception {

        File outputFile = new File(outputPath);

        File parent = outputFile.getParentFile();

        if (parent != null && !parent.exists()) {
            parent.mkdirs();
        }

        objectMapper.writeValue(
                outputFile,
                report
        );

        System.out.println(
                "JSON report generated successfully."
        );

        System.out.println(
                "Location: "
                        + outputFile.getAbsolutePath()
        );
    }
}