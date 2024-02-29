package com.practo.controller;

import com.practo.payload.DoctorDto;
import com.practo.payload.DoctorReviewDto;
import com.practo.payload.ReviewDto;
import com.practo.service.DoctorService;
import com.practo.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private DoctorService doctorService;


    @PostMapping
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto) {
        ReviewDto reviewDtos = reviewService.createReview(reviewDto);
        return new ResponseEntity<>(reviewDtos, HttpStatus.CREATED);
    }

    @GetMapping("/{doctorId}")
    public ResponseEntity<DoctorReviewDto> getReviewByDoctorId(@PathVariable long doctorId) {
        DoctorDto doctor = doctorService.findByDoctorId(doctorId);
        List<ReviewDto> reviews = reviewService.getReviewByDoctorId(doctorId);
        double totalRatings = 0;
        for (ReviewDto review : reviews) {
            totalRatings += review.getRating();
        }
        double averageRating = totalRatings / reviews.size();
        double ratingPercentage=(averageRating / 5) * 100;

        DoctorReviewDto reviewDto = new DoctorReviewDto();
        reviewDto.setDoctor(doctor);
        reviewDto.setReviews(reviews);
        reviewDto.setRatingPercentage(ratingPercentage);
        return new ResponseEntity<>(reviewDto, HttpStatus.CREATED);
    }
}
