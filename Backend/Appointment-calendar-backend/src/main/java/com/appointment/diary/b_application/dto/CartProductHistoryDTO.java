package com.appointment.diary.b_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CartProductHistoryDTO {
    private Long id;
    private Long cartHistoryId;
    private Long productId;
    private Integer quantity;
    private Double priceAtPurchase;
    private LocalDateTime createdAt;
}
