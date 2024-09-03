package com.appointment.diary.c_domain.model.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class CartBO {
    private Long id;
    private UserBO user;
    private List<CartProductBO> cartProducts;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Double calculateTotalAmount() {
        return cartProducts.stream()
                .mapToDouble(CartProductBO::getAmount)
                .sum();
    }

    public boolean isEmpty() {
        return cartProducts.isEmpty();
    }

}
