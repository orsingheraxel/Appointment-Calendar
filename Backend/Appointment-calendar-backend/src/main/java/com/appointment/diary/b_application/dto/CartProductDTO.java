package com.appointment.diary.b_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CartProductDTO {
    private Long id;
    private Long cartId;
    private Long productId;
    private Integer quantity;
    private Double amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
