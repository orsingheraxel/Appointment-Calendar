package com.appointment.diary.c_domain.repository;

import com.appointment.diary.c_domain.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
}
