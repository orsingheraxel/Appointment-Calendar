package com.appointment.diary.b_application.command;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CreateAppointmentCommand {

    private Long userId;            // ID del usuario que realiza la cita
    private Long serviceId;         // ID del servicio para el que se realiza la cita
    private LocalDateTime appointmentTime; // Fecha y hora de la cita

    // Constructor
    public CreateAppointmentCommand(Long userId, Long serviceId, LocalDateTime appointmentTime) {
        this.userId = userId;
        this.serviceId = serviceId;
        this.appointmentTime = appointmentTime;
    }
}

