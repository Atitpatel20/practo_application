package com.practo.service;

import com.practo.payload.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(ReviewDto reviewDto);

    List<ReviewDto> getReviewByDoctorId(long doctorId);
}
