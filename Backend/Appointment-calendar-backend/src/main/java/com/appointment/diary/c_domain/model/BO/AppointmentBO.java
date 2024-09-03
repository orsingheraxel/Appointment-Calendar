package com.appointment.diary.c_domain.model.BO;

import com.appointment.diary.c_domain.model.StatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class AppointmentBO {

    private Long id;
    private UserBO user;
    private ServiceBO service;
    private LocalDateTime appointmentTime;
    private boolean isAnswered;
    private StatusEnum status;

    // Métodos de negocio

    public void confirmAppointment() {
        // Lógica para confirmar la cita
        if (this.appointmentTime.isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Cannot confirm an appointment in the past");
        }
        this.isAnswered = true;
        this.status = StatusEnum.CONFIRMED;
    }

    public boolean validDateTime(LocalDateTime appointmentTime){
            //Verificar si appointmentTime es null
            if (appointmentTime == null) {
                throw new IllegalArgumentException("Appointment time cannot be null.");
            }

            LocalDateTime now = LocalDateTime.now();

            // Verificar si el año es igual al año actual
            if (appointmentTime.getYear() != now.getYear()) {
                throw new IllegalArgumentException("Appointment year must be the current year.");
            }

            // Verificar si el mes es igual al mes actual
            if (appointmentTime.getMonth() != now.getMonth()) {
                throw new IllegalArgumentException("Appointment month must be the current month.");
            }

            // Verificar si la hora está entre las habiles
            int hour = appointmentTime.getHour();
            if (hour < 9 || hour > 18) {
                throw new IllegalArgumentException("Appointment time must be between 09:00 and 18:00.");
            }

        return true;
    }

    public void cancelAppointment() {
        // Lógica para cancelar la cita
        if (status == StatusEnum.CANCELLED) {
            throw new IllegalStateException("Cannot cancel a cancelled appointment");
        }
        this.isAnswered = true;
        this.setStatus(StatusEnum.CANCELLED);
    }
}

