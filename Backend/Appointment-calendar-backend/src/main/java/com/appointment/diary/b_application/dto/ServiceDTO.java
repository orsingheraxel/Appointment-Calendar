package com.appointment.diary.b_application.dto;

import com.appointment.diary.c_domain.model.Appointment;
import com.appointment.diary.c_domain.model.Photo;
import com.appointment.diary.c_domain.model.Review;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private Long providerId;
    private Set<AppointmentDTO> appointments = new HashSet<>();
    private Set<ReviewDTO> reviews = new HashSet<>();
    private Set<PhotoDTO> photos = new HashSet<>();
}
