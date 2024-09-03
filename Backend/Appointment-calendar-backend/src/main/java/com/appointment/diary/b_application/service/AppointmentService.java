package com.appointment.diary.b_application.service;

import com.appointment.diary.b_application.dto.AppointmentDTO;
import com.appointment.diary.b_application.dto.ReviewDTO;
import com.appointment.diary.c_domain.model.Appointment;
import com.appointment.diary.c_domain.model.Review;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public interface AppointmentService {

    List<AppointmentDTO> getAllAppointments();

    Optional<AppointmentDTO> getAppointmentById(Long id);

    AppointmentDTO createAppointment(AppointmentDTO appointment);

    AppointmentDTO updateAppointment(Long id, AppointmentDTO appointment);

    void deleteAppointment(Long id);



    List<AppointmentDTO> findByServiceId(Long serviceId);

    List<AppointmentDTO> findByUserId(Long userId);

    void confirmAppointment(Long id);

    void cancelAppointment(Long id);
}
