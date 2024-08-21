package com.appointment.diary.b_application.service;

import com.appointment.diary.c_domain.model.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<Review> getAllReviews();

    Optional<Review> getReviewById(Long id);

    Review createReview(Review review);

    Review updateReview(Long id, Review review);

    void deleteReview(Long id);
}
