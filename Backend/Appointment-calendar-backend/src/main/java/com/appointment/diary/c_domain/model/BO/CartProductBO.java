package com.appointment.diary.c_domain.model.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CartProductBO {
    private Long id;
    private CartBO cart;
    private ProductBO product;
    private Integer quantity;
    private Double amount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
