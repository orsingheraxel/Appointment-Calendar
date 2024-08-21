package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.ReviewDTO;
import com.appointment.diary.c_domain.model.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public ReviewDTO toDTO(Review review) {
        return ReviewDTO.builder()
                .id(review.getId())
                .userId(review.getUser().getId())
                .serviceId(review.getService().getId())
                .comment(review.getComment())
                .rating(review.getRating())
                .createdAt(review.getCreatedAt().toString())
                .build();
    }

    public Review toEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setId(reviewDTO.getId());
        review.setComment(reviewDTO.getComment());
        review.setRating(reviewDTO.getRating());
        // Tendrás que asignar manualmente las entidades UserEntity y ServiceEntity desde los IDs si es necesario.
        return review;
    }
}
