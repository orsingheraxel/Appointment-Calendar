package com.appointment.diary.c_domain.model.BO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
public class ServiceBO {
    private Long id;
    private String name;
    private String description;
    private double price;
    private UserBO provider;
    private Set<AppointmentBO> appointments;
    private Set<ReviewBO> reviews;
    private Set<PhotoBO> photos;

    // Métodos de negocio

    public void addReview(ReviewBO review) {
        // Lógica para agregar una reseña al servicio
        if (review == null) {
            throw new IllegalArgumentException("Review cannot be null");
        }
        reviews.add(review);
    }

    public void updatePrice(double newPrice) {
        // Lógica para actualizar el precio del servicio
        if (newPrice <= 0) {
            throw new IllegalArgumentException("Price must be greater than zero");
        }
        this.price = newPrice;
    }
}
