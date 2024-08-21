package com.appointment.diary.b_application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long providerId;
}
