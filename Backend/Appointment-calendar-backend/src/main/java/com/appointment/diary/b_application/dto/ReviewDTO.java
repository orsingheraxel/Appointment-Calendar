package com.appointment.diary.b_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private Long userId;
    private Long serviceId;
    private String comment;
    private int rating;
    private String createdAt;  // Asumiendo que es un String, puedes cambiarlo si necesitas
}
