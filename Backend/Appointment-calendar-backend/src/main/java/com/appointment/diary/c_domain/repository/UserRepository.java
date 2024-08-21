package com.appointment.diary.c_domain.repository;

import com.appointment.diary.c_domain.model.Appointment;
import com.appointment.diary.c_domain.model.Review;
import com.appointment.diary.c_domain.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Obtener todas las citas de un usuario específico
    @Query("SELECT a " +
            "FROM Appointment a " +
            "WHERE a.user.id = :userId")
    List<Appointment> findAppointmentsByUserId(@Param("userId") Long userId);

    // Obtener todas las reseñas realizadas por un usuario específico
    @Query("SELECT r " +
            "FROM Review r " +
            "WHERE r.user.id = :userId")
    List<Review> findReviewsByUserId(@Param("userId") Long userId);

    //Obtener las citas confirmadas
    @Query("SELECT a " +
            "FROM Appointment a " +
            "WHERE a.user.id = :userId AND a.isConfirmed=TRUE")
    List<Appointment> findConfirmedAppointments(@Param("userId") Long userId);
}
