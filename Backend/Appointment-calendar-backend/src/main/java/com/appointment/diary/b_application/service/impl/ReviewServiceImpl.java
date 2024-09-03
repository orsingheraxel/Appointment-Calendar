package com.appointment.diary.b_application.service.impl;

import com.appointment.diary.b_application.dto.ReviewDTO;
import com.appointment.diary.b_application.mapper.ReviewMapper;
import com.appointment.diary.b_application.service.ReviewService;
import com.appointment.diary.c_domain.exception.InvalidRequestException;
import com.appointment.diary.c_domain.model.Review;
import com.appointment.diary.b_application.port.out.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    
    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAll().stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ReviewDTO> getReviewById(Long id) {
        return reviewRepository.findById(id)
                .map(reviewMapper::toDTO);
    }

    @Override
    public ReviewDTO createReview(ReviewDTO reviewDTO) {
        Review review = reviewMapper.toEntity(reviewDTO);

        Review savedreview = reviewRepository.save(review);

        return reviewMapper.toDTO(savedreview);
    }

    @Override
    public ReviewDTO updateReview(Long id, ReviewDTO reviewDTO) {

        if (reviewRepository.existsById(id)){
            Review review = reviewMapper.toEntity(reviewDTO);
            review.setId(id);
            return reviewMapper.toDTO(review);
        }else{
            throw new InvalidRequestException("Review with ID " + id + " not found");
        }
    }

    @Override
    public void deleteReview(Long id) {
        if (reviewRepository.existsById(id)){
            reviewRepository.deleteById(id);
        }else{
            throw new InvalidRequestException("Review with ID " + id + " not found");
        }
    }

    @Override
    public List<ReviewDTO> findByUserId(Long userId) {
        return reviewRepository.findByUserId(userId).stream()
                .map(reviewMapper::toDTO)
                .collect(Collectors.toList());
    }
}
