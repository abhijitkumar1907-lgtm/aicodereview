package com.codereviewer.service;

import com.codereviewer.ai.AIReviewer;
import com.codereviewer.analyzer.CodeReviewer;
import com.codereviewer.database.ReviewRepository;
import com.codereviewer.model.ReviewResult;
import com.codereviewer.utils.JsonReportGeneration;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReviewService {

    private final CodeReviewer codeReviewer;
    private final AIReviewer aiReviewer;
    private final ReviewRepository reviewRepository;
    private final JsonReportGeneration jsonReportGeneration;

    public ReviewService(
            ReviewRepository reviewRepository) {

        this.codeReviewer =
                new CodeReviewer();

        this.aiReviewer =
                new AIReviewer();

        this.reviewRepository =
                reviewRepository;

        this.jsonReportGeneration =
                new JsonReportGeneration();
    }


    // =====================================================
    // CREATE REVIEW
    // =====================================================

    public ReviewResult review(
            String sourceCode,
            String filename) {

        // 1. Static analysis
        ReviewResult result =
                codeReviewer.review(
                        sourceCode,
                        filename
                );


        // 2. AI review
        String aiReview =
                aiReviewer.review(
                        sourceCode,
                        result.getIssues()
                );


        // 3. Add AI review
        result.setAiReview(
                aiReview
        );


        // 4. Save to database
        reviewRepository.saveReview(
                result,
                filename,
                aiReview
        );


        // 5. Save to JSON history
        jsonReportGeneration.generateReport(
                result
        );


        return result;
    }


    // =====================================================
    // GET ALL REVIEWS
    // =====================================================

    public List<Map<String, Object>> getAllReviews() {

        return reviewRepository.getAllReviews();
    }


    // =====================================================
    // GET REVIEW BY ID
    // =====================================================

    public Map<String, Object> getReviewById(
            long id) {

        return reviewRepository.getReviewById(
                id
        );
    }
}