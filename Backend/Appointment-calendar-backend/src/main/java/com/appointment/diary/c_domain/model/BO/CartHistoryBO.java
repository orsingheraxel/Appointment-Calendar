package com.appointment.diary.c_domain.model.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CartHistoryBO {
    private Long id;
    private UserBO user;
    private Double totalPrice;
    private List<CartProductHistoryBO> cartProductHistories;
    private LocalDateTime createdAt;

    private Double calculateTotalPrice() {
        return cartProductHistories.stream()
                .mapToDouble(CartProductHistoryBO::getPriceAtPurchase)
                .sum();
    }
}
