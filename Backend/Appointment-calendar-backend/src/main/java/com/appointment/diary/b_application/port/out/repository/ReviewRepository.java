package com.appointment.diary.b_application.port.out.repository;

import com.appointment.diary.b_application.dto.ReviewDTO;
import com.appointment.diary.c_domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // Obtener todas las reseñas realizadas por un usuario específico
    List<Review> findByUserId(Long userId);
}
