package com.appointment.diary.c_domain.repository;

import com.appointment.diary.c_domain.model.Appointment;
import com.appointment.diary.c_domain.model.Review;
import com.appointment.diary.c_domain.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {

    // Obtener todas las reseñas de un servicio específico
    List<Review> findReviewsByServiceId(Long serviceId);

    // Obtener todas las citas de un servicio específico
    List<Appointment> findAppointmentsByServiceId(Long serviceId);

    // Buscar servicios por nombre --CONTAINING FUNCIONA COMO UN LIKE
    List<Service> findByNameContaining(String name);

    // Buscar servicios por descripcion
    @Query("SELECT s " +
            "FROM Service s " +
            "WHERE s.description LIKE %:descrption%")
    List<Service> findByDescriptionContaining(@Param("description") String description);
}
