package com.appointment.diary.c_domain.model.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class PhotoBO {
    private Long id;
    private String url;
    private Long serviceId; // Puede ser null si la foto está asociada a un producto
    private Long productId; // Puede ser null si la foto está asociada a un servicio
    private LocalDateTime uploadedAt;
}
