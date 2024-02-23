package com.practo.service.Impl;

import com.practo.entity.Doctor;
import com.practo.entity.Patient;
import com.practo.entity.Review;
import com.practo.exception.ResourceNotFoundException;
import com.practo.payload.DoctorDto;
import com.practo.payload.ReviewDto;
import com.practo.repository.DoctorRepository;
import com.practo.repository.PatientRepository;
import com.practo.repository.ReviewRepository;
import com.practo.service.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Doctor doctor = doctorRepository.findById(reviewDto.getDoctorId()).orElseThrow(
                () -> new ResourceNotFoundException("doctor not found with id: " + reviewDto.getDoctorId())
        );
        Patient patient = patientRepository.findById(reviewDto.getPatientId()).orElseThrow(
                () -> new ResourceNotFoundException("patient not found with id: " + reviewDto.getPatientId())
        );
        if (doctor != null || patient != null) {
            Review review = mapToEntity(reviewDto);
            Review saveReview = reviewRepository.save(review);
            return mapToDto(saveReview);
        } else {
            return null;
        }
    }

    @Override
    public List<ReviewDto> getReviewByDoctorId(long doctorId) {
        List<Review> reviews = reviewRepository.findByDoctorId(doctorId);
        List<ReviewDto> collect = reviews.stream().map(r -> mapToDto(r)).collect(Collectors.toList());
        return collect;
    }

    Review mapToEntity(ReviewDto reviewDto) {
        Review dto = modelMapper.map(reviewDto, Review.class);
        return dto;
    }

    ReviewDto mapToDto(Review review) {
        ReviewDto dto = modelMapper.map(review, ReviewDto.class);
        return dto;
    }
}
