package com.appointment.diary.b_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CartHistoryDTO {
    private Long id;
    private Long userId;
    private Double totalPrice;
    private List<CartProductHistoryDTO> cartProductHistories;
    private LocalDateTime createdAt;
}
