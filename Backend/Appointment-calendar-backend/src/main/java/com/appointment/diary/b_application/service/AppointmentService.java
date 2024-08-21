package com.appointment.diary.b_application.service;

import com.appointment.diary.c_domain.model.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentService {

    List<Appointment> getAllAppointments();

    Optional<Appointment> getAppointmentById(Long id);

    Appointment createAppointment(Appointment appointment);

    Appointment updateAppointment(Long id, Appointment appointment);

    void deleteAppointment(Long id);
}
