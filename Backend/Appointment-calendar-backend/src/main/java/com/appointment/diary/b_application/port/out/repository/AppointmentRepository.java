package com.appointment.diary.b_application.port.out.repository;

import com.appointment.diary.c_domain.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface
AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Obtener todas las citas asociadas a un servicio específico
    List<Appointment> findByServiceId(Long serviceId);

    // Obtener todas las citas asociadas a un usuario específico
    List<Appointment> findByUserId(Long userId);

    // Confirmar una cita
    @Modifying
    @Query("UPDATE Appointment a SET a.isAnswered = TRUE, a.status = 'CONFIRMED' WHERE a.id = :id")
    void confirmAppointment(@Param("id") Long id);

    // Cancelar una cita
    @Modifying
    @Query("UPDATE Appointment a SET a.isAnswered = TRUE, a.status = 'CANCELLED' WHERE a.id = :id")
    void cancelAppointment(@Param("id") Long id);
}
