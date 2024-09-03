package com.appointment.diary.c_domain.model.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class ReviewBO {
    private Long id;
    private UserBO user;
    private ServiceBO service;
    private String comment;
    private int rating;
    private LocalDateTime createdAt;

    // Constructor
    public ReviewBO(UserBO user, ServiceBO service, String comment, int rating, LocalDateTime createdAt) {
        this.user = user;
        this.service = service;
        this.comment = comment;
        this.rating = rating;
        this.createdAt=createdAt;
    }

    // Métodos de negocio

    public void updateComment(String newComment) {
        // Lógica para actualizar el comentario
        if (newComment == null || newComment.isEmpty()) {
            throw new IllegalArgumentException("Comment cannot be null or empty");
        }
        this.comment = newComment;
    }

    public void updateRating(int newRating) {
        // Lógica para actualizar la calificación
        if (newRating < 1 || newRating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.rating = newRating;
    }
}
