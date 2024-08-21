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
public class AppointmentDTO {
    private Long id;
    private Long userId;
    private Long serviceId;
    private LocalDateTime appointmentTime;
    private boolean isConfirmed;
    private String status;
}
