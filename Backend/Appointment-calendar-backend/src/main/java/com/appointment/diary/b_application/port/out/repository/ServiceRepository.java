package com.appointment.diary.b_application.port.out.repository;

import com.appointment.diary.c_domain.model.Appointment;
import com.appointment.diary.c_domain.model.Review;
import com.appointment.diary.c_domain.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}

