package com.codereviewer.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ReviewRequest {

    @JsonProperty("filename")
    @JsonAlias({"fileName"})
    private String filename;

    @JsonProperty("sourceCode")
    private String sourceCode;

    public ReviewRequest() {
    }

    public ReviewRequest(
            String filename,
            String sourceCode) {

        this.filename = filename;
        this.sourceCode = sourceCode;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(
            String filename) {

        this.filename = filename;
    }

    public String getSourceCode() {
        return sourceCode;
    }

    public void setSourceCode(
            String sourceCode) {

        this.sourceCode = sourceCode;
    }
}