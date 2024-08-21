package com.appointment.diary.b_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PhotoDTO {
    private Long id;
    private String url;
    private Long serviceId;
    private Long providerId;
    private String uploadedAt;  // Asumiendo que es un String, puedes cambiarlo si necesitas
}
