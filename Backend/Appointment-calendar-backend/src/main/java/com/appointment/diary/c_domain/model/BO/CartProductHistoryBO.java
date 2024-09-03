package com.appointment.diary.c_domain.model.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CartProductHistoryBO {
    private Long id;
    private CartHistoryBO cartHistory;
    private ProductBO product;
    private Integer quantity;
    private Double priceAtPurchase;
    private LocalDateTime createdAt;
}
