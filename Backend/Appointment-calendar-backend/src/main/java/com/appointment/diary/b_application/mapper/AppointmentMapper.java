package com.appointment.diary.b_application.mapper;

import com.appointment.diary.b_application.dto.AppointmentDTO;
import com.appointment.diary.c_domain.model.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AppointmentMapper {

    public AppointmentDTO toDTO(Appointment appointment) {
        return AppointmentDTO.builder()
                .id(appointment.getId())
                .userId(appointment.getUser().getId())
                .serviceId(appointment.getService().getId())
                .appointmentTime(appointment.getAppointmentTime())
                .isConfirmed(appointment.isConfirmed())
                .status(appointment.getStatus())
                .build();
    }

    public Appointment toEntity(AppointmentDTO appointmentDTO) {
        Appointment appointment = new Appointment();
        appointment.setId(appointmentDTO.getId());
        appointment.setAppointmentTime(appointmentDTO.getAppointmentTime());
        appointment.setConfirmed(appointmentDTO.isConfirmed());
        appointment.setStatus(appointmentDTO.getStatus());
        // Tendrás que asignar manualmente las entidades UserEntity y ServiceEntity desde los IDs si es necesario.
        return appointment;
    }
}
