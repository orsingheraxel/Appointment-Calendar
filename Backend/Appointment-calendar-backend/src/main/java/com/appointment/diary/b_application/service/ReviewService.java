package com.appointment.diary.b_application.service;

import com.appointment.diary.b_application.dto.ReviewDTO;
import com.appointment.diary.c_domain.model.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ReviewService {

    List<ReviewDTO> getAllReviews();

    Optional<ReviewDTO> getReviewById(Long id);

    ReviewDTO createReview(ReviewDTO review);

    ReviewDTO updateReview(Long id, ReviewDTO review);

    void deleteReview(Long id);

    List<ReviewDTO> findByUserId(Long userId);
}
